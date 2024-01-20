package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.memo.MemoRepository;
import com.maskun.projectdiary.web.dto.MemoSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoRepository memoRepository;
    public List<Memo> findMemoList(LocalDate date) {
        return memoRepository.findByMemoDate(date);
    }

    public Memo findMemoByNo(Long no) {
        return memoRepository.findMemoByMemoNo(no).orElse(null);
    }

    public List<Memo> findUserMemoListByDate(String userId, LocalDate date) {
        return memoRepository.findMemosByUserIdAndMemoDateOrderByCreatedate(userId, date);
    }

    /**
     * @param userId
     * @param date
     * @param reqMemoList
     * @return
     */
    //TODO 리팩토링
    @Transactional
    public boolean updateUserDateMemo(String userId, LocalDate date, List<MemoSaveDto> reqMemoList)throws IllegalArgumentException {
        //수정할 요청리스트 구분하기
        List<MemoSaveDto> updateReqList = filterNeedToUpdate(reqMemoList);
        //데이터가 요청 인증정보와 일관성이 있는지 확인하기
        verifyUpdateRequest(userId, date, updateReqList.stream().map(MemoSaveDto::memoNo).toList());
        //추가할 리스트
        List<MemoSaveDto> addingList = reqMemoList.stream()
                .filter(m -> m.memoNo() == null && !(m.memoContent().isBlank()))
                .toList();

        //해당 날짜에 남길 메모 넘버를 구하기
        List<Long> remainMemoNos = updateReqList.stream()
                .map(MemoSaveDto::memoNo)
                .toList();

        //해달날짜의 나머지 메모를 삭제하기
        removeOtherDateMemos(userId, date, remainMemoNos);
        //메모를 수정하기
        updateMemos(updateReqList);
        //메모를 추가하기
        saveMemos(userId, date, addingList);

        //완료하기
        return true;

    }

    /** update 용 요청 객체만 추림. No가 null인 객체는 add 건이거나 garbage이므로 제외
     * @param reqMemoList
     * @return
     */
    private static List<MemoSaveDto> filterNeedToUpdate(List<MemoSaveDto> reqMemoList) {
        return reqMemoList.stream()
                .filter(m -> m.memoNo() != null)
                .toList();
    }

    private void verifyUpdateRequest(String userId, LocalDate date, List<Long> reqMemoNos) {
        List<Memo> orginMemoList = memoRepository.findMemosByUserIdAndMemoDateOrderByCreatedate(userId, date);
        Set<Long> originMemoNos = orginMemoList.stream().map(Memo::getMemoNo).collect(Collectors.toSet());
        boolean isMatch = originMemoNos.containsAll(reqMemoNos);
        if(!isMatch){
            throw new IllegalArgumentException("요청 인증정보와 데이터의 일관성이 없습니다. receive="+reqMemoNos.toString()+"found="+originMemoNos.toString());
        }
    }

    private void saveMemos(String userId, LocalDate date, List<MemoSaveDto> addingList) {
        for(MemoSaveDto dto : addingList){
            Memo addingMemo= Memo.builder()
                    .memoDate(date)
                    .userId(userId)
                    .memoContent(dto.memoContent())
                    .createdate(LocalDateTime.now())
                    .build();
            memoRepository.save(addingMemo);
        }
    }

    private void updateMemos(List<MemoSaveDto> modifingList) {
        for(MemoSaveDto dto : modifingList){
            Optional<Memo> memo = memoRepository.findMemoByMemoNo(dto.memoNo());
            memo.ifPresent(m -> m.updateContent(dto.memoContent()));
        }
    }

    private void removeOtherDateMemos(String userId, LocalDate date, List<Long> remainMemoNos){
        List<Memo> memoList = memoRepository.findMemosByUserIdAndMemoDateOrderByCreatedate(userId, date);
        List<Long> deletingNoList = memoList.stream()
                .map(Memo::getMemoNo)
                .filter(no -> remainMemoNos.stream().noneMatch(Predicate.isEqual(no)))
                .toList();

        if(deletingNoList.isEmpty()){
            return;
        }
        memoRepository.deleteMemoByMemoNoIn(deletingNoList);
    }

}
