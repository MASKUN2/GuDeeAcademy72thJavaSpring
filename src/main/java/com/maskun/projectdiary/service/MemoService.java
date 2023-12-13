package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.MemoMapper;
import com.maskun.projectdiary.domain.entity.User;
import com.maskun.projectdiary.domain.entity.Memo;
import com.maskun.projectdiary.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemoService {
    private final MemoMapper memoMapper;

    /**
     * 해당 날짜의 메모리스트를 가져옵니다.
     */
    public List<Memo> getDateMemoList(LocalDate date, String userId) {
        List<Memo> dateMemoList = memoMapper.selectDateMemoList(date, userId);
        return dateMemoList;
    }

    /**
     * 메모를 수정합니다.
     */
    @Transactional
    public boolean setMemo(int memoNo, String memoContent) {
        int numberOfRowsAffected = memoMapper.updateMemo(memoNo,memoContent);
        return (numberOfRowsAffected == 1)? true:false;
    }
    /**
     * 메모를 삭제합니다.
     */
    @Transactional
    public boolean removeMemo(int memoNo) {
        int numberOfRowsAffected = memoMapper.deleteMemo(memoNo);
        return (numberOfRowsAffected == 1)? true:false;
    }

    /**
     * 메모를 추가합니다.
     */
    @Transactional
    public boolean addMemo(User user, RequestDto<Memo> dto) {
        Memo memo = dto.toServiceDto();
        int numberOfRowsAffected = memoMapper.insertMemo(user,memo);
        return (numberOfRowsAffected == 1)? true:false;
    }
}
