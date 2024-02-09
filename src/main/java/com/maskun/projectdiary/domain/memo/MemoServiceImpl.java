package com.maskun.projectdiary.domain.memo;

import com.maskun.projectdiary.core.exception.DeleteFailureException;
import com.maskun.projectdiary.core.exception.MismatchRequestToDbRecordException;
import com.maskun.projectdiary.web.dto.MemoPutReqDto;
import com.maskun.projectdiary.web.dto.Pagination;
import com.maskun.projectdiary.web.dto.PaginationImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

    /**
     * <p>수정단위(일자)에 해당하는 메모를 수정,삭제,추가합니다.</p>
     *
     * @param userId      웹 요청의 사용자 아이디
     * @param date        해당일자
     * @param reqUserDateMemoList 웹요청으로 받은 해당일자의 메모 목표
     */
    @Override
    @Transactional
    public void updateUserDateMemoList(String userId, LocalDate date, List<MemoPutReqDto> reqUserDateMemoList)throws MismatchRequestToDbRecordException{
        //가비지 메모를 제거하기
        reqUserDateMemoList.removeIf(memoPutReqDto -> !StringUtils.hasText(memoPutReqDto.memoContent())&&Objects.isNull(memoPutReqDto.memoNo()));
        //전체 삭제인지 확인하고 맞으면 삭제하기
        if(reqUserDateMemoList.isEmpty()){
            deleteAllMemoByUserIdAndDate(userId, date);
            return;
        }
        
        //일부삭제인 경우 아래로
        //삭제/수정/추가할 메모를 구별하기
        //일부를 삭제하기
        //수정할 데이터의 일관성 확인하기
        //수정하기
        //추가하기
        //리턴하기


        //데이터가 요청 인증정보와 일관성이 있는지 확인하기
        try {
            List<Long> userMemoNoList = reqUserDateMemoList.stream()
                    .map(MemoPutReqDto::memoNo)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
            verifyUpdateRequest(userId, date, userMemoNoList);
        }catch (IllegalArgumentException e){
            log.error("요청정보와 실제 DB의 정보가 일치하지 않습니다.", e);
            throw new MismatchRequestToDbRecordException(e);
        }
        //수정할 요청리스트 구분하기
        List<MemoPutReqDto> updateReqList = filterNeedToUpdate(reqUserDateMemoList);

        //추가할 리스트 만들기 (빈 메모는 대상에 올리지 않음)
        List<MemoPutReqDto> addingList = reqUserDateMemoList.stream()
                .filter(m -> m.memoNo() == null && !(m.memoContent().isBlank()))
                .toList();

        //해당 날짜에 남길 메모 넘버를 구하기
        List<Long> remainMemoNos = updateReqList.stream()
                .map(MemoPutReqDto::memoNo)
                .toList();

        //해달날짜의 남기는 메모를 제외한 나머지 메모를 삭제하기
        removeOtherDateMemos(userId, date, remainMemoNos);
        //메모를 수정하기
        updateMemosContent(updateReqList);
        //메모를 추가하기
        saveMemos(userId, date, addingList);

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

    @Override
    public Pagination<Memo> retrieveUserMemoByKeywordPagination(String userId, String keyword, Pageable pageable) {
        Page<Memo> memoPage = memoRepository.findMemoByUserIdAndMemoContentContainsOrderByCreatedateDesc(userId, keyword, pageable);
        return PaginationImpl.fromPage(memoPage);
    }

    /**
     * update 용 요청 객체만 추림. No가 null인 객체는 add 건이거나 garbage이므로 제외
     *
     * @param reqMemoList
     * @return
     */
    private static List<MemoPutReqDto> filterNeedToUpdate(List<MemoPutReqDto> reqMemoList) {
        return reqMemoList.stream()
                .filter(m -> m.memoNo() != null)
                .toList();
    }

    /**
     * 사용자의 식별자(ID)가 수정요청을 받은 메모의 작성자 정보(ID)와 일치하는지 확인합니다.
     *
     * @param userId     HTTP요청자의 로그인아이디
     * @param date       메모 수정단위(일)에 대하는 해당 날짜
     * @param reqMemoNos 수정을 요청받은 메모의 식별정보 (메모번호)
     * @throws IllegalArgumentException 요청과 DB 대조시 일치하지 않으면 발생
     */
    private void verifyUpdateRequest(String userId, LocalDate date, List<Long> reqMemoNos) throws IllegalArgumentException {
        List<Memo> orginMemoList = memoRepository.findMemosByUserIdAndMemoDateOrderByCreatedate(userId, date);
        Set<Long> originMemoNos = orginMemoList.stream().map(Memo::getMemoNo).collect(Collectors.toSet());
        boolean isMatch = originMemoNos.containsAll(reqMemoNos);
        if (!isMatch) {
            throw new IllegalArgumentException("요청 인증정보와 데이터의 일관성이 없습니다. receive=" + reqMemoNos.toString() + "found=" + originMemoNos.toString());
        }
    }

    /**
     * <p> 다수의 메모를 저장합니다.</p>
     */
    private void saveMemos(String userId, LocalDate date, List<MemoPutReqDto> addingList) {
        for (MemoPutReqDto dto : addingList) {
            Memo addingMemo = Memo.builder()
                    .memoDate(date)
                    .userId(userId)
                    .memoContent(dto.memoContent())
                    .createdate(LocalDateTime.now())
                    .build();
            memoRepository.save(addingMemo);
        }
    }

    /**
     * <p> Dirty check, 메모의 내용을 수정합니다.</p>
     */
    private void updateMemosContent(List<MemoPutReqDto> modifingList) {
        for (MemoPutReqDto dto : modifingList) {
            Optional<Memo> memo = memoRepository.findMemoByMemoNo(dto.memoNo());
            memo.ifPresent(m -> m.updateContent(dto.memoContent()));
        }
    }

    /**
     * <p>해당 일자에 저장된 메모 중 남기려는 메모를 제외하고 삭제합니다.</p>
     *
     * @param userId        웹 요청의 사용자 아이디
     * @param date          해당 날짜
     * @param remainMemoNos 남기고자 하는 메모의 번호리스트
     */
    private void removeOtherDateMemos(String userId, LocalDate date, List<Long> remainMemoNos) {
        List<Memo> memoList = memoRepository.findMemosByUserIdAndMemoDateOrderByCreatedate(userId, date);
        List<Long> deletingNoList = memoList.stream()
                .map(Memo::getMemoNo)
                .filter(no -> remainMemoNos.stream().noneMatch(Predicate.isEqual(no)))
                .toList();

        if (deletingNoList.isEmpty()) {
            return;
        }
        memoRepository.deleteMemoByMemoNoIn(deletingNoList);
    }
}
