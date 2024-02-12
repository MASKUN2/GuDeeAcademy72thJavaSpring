package com.maskun.projectdiary.domain.memo;

import com.maskun.projectdiary.core.exception.DeleteFailureException;
import com.maskun.projectdiary.web.dto.Pagination;
import com.maskun.projectdiary.web.dto.PaginationImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService{
    private final MemoRepository memoRepository;

    @Override
    public List<Memo> findUserMemoListByDate(String userId, LocalDate date) {
        return memoRepository.findMemosByUserIdAndMemoDateOrderByCreatedate(userId, date);
    }

    @Override
    public Pagination<Memo> retrieveUserMemoByKeywordPagination(String userId, String keyword, Pageable pageable) {
        Page<Memo> memoPage = memoRepository.findMemoByUserIdAndMemoContentContainsOrderByCreatedateDesc(userId, keyword, pageable);
        return PaginationImpl.fromPage(memoPage);
    }

    /**
     * <p>수정단위(일자)에 해당하는 메모를 수정,삭제,추가합니다.</p>
     *
     * @param userId      웹 요청의 사용자 아이디
     * @param date        해당일자
     * @param reqDtoList 웹요청으로 받은 해당일자의 메모 목표
     */
    @Override
    @Transactional
    public void updateUserDateMemoList(String userId, LocalDate date, List<MemoPutReqDto> req)throws IllegalArgumentException{
        List<MemoPutReqDto> reqDtoList = req.stream().filter(dto -> !dto.IsReqTypeIgnore()).toList();
        boolean isCleanUpDateMemos = reqDtoList.isEmpty();
        if(isCleanUpDateMemos){
            deleteAllMemoByUserIdAndDate(userId, date);
            return;
        }
        List<MemoPutReqDto> remainAndUpdateList = reqDtoList.stream()
                .filter(MemoPutReqDto::IsReqTypeRemainAndUpdate)
                .toList();
        List<MemoPutReqDto> addList = reqDtoList.stream()
                .filter(MemoPutReqDto::IsReqTypeInsert)
                .toList();
        updateMemosAndDeleteOthers(userId, date, remainAndUpdateList);
        InsertMemos(userId, date, addList);
    }

    private void deleteAllMemoByUserIdAndDate(String userId, LocalDate date) {
        if(memoRepository.findByUserIdAndMemoDate(userId, date).isEmpty()){
            return;
        }
        memoRepository.deleteAllByUserIdAndMemoDate(userId, date);
        List<Memo> remainMemoList = memoRepository.findByUserIdAndMemoDate(userId, date);
        if(remainMemoList.isEmpty()){
            return;
        }
        throw new DeleteFailureException("삭제를 시도했으나 실제로 삭제되지 않았습니다. 남아있는 메모리스트 : "+ remainMemoList.toString());
    }

    private void updateMemosAndDeleteOthers(String userId, LocalDate date, List<MemoPutReqDto> remainAndUpdateList)throws IllegalArgumentException{
        List<Long> deleteTargetMemoNos = memoRepository.findByUserIdAndMemoDate(userId, date).stream().map(Memo::getMemoNo).collect(Collectors.toList());
        for (MemoPutReqDto dto : remainAndUpdateList) {
            Optional<Memo> memoOpt = memoRepository.findMemoByMemoNo(dto.getMemoNo());
            Memo foundMemo = memoOpt.orElseThrow(() -> new IllegalArgumentException("해당 엔티티객체를 찾을 수 없습니다. No = "+ dto.getMemoNo()));
            foundMemo.updateContent(dto.getMemoContent());
            deleteTargetMemoNos.removeIf(Predicate.isEqual(foundMemo.getMemoNo()));
        }
        memoRepository.deleteMemosByMemoNoIn(deleteTargetMemoNos);
    }

    private void InsertMemos(String userId, LocalDate date, List<MemoPutReqDto> addingList) {
        for (MemoPutReqDto dto : addingList) {
            Memo addingMemo = dto.toEntityForInsert(userId, date);
            memoRepository.save(addingMemo);
        }
    }

}
