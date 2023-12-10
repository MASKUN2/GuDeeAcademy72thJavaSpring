package com.maskun.projectdiary.service;

import com.maskun.projectdiary.mapper.MemoMapper;
import com.maskun.projectdiary.vo.domain.Member;
import com.maskun.projectdiary.vo.domain.Memo;
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

    public List<Memo> getDateMemoList(LocalDate localDate, Member member) {
        String memberId = member.getMemberId();
        List<Memo> dateMemoList = memoMapper.selectDateMemoList(memberId, localDate);
        return dateMemoList;
    }

    @Transactional
    public boolean setMemo(int memoNo, String memoContent) {
        int numberOfRowsAffected = memoMapper.updateMemo(memoNo,memoContent);
        return (numberOfRowsAffected == 1)? true:false;
    }

    @Transactional
    public boolean removeMemo(int memoNo) {
        int numberOfRowsAffected = memoMapper.deleteMemo(memoNo);
        return (numberOfRowsAffected == 1)? true:false;
    }

    @Transactional
    public boolean addMemo(String memberId, LocalDate date, String memo) {
        int numberOfRowsAffected = memoMapper.insertMemo(memberId,date,memo);
        return (numberOfRowsAffected == 1)? true:false;
    }
}
