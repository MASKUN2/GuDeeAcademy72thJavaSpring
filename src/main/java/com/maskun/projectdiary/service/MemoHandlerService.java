package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.memo.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Service
public class MemoHandlerService implements MemoService {
    private final MemoRepository memoRepository;
    @Override
    public List<Memo> findMemoList(LocalDate date) {
        return memoRepository.findByMemoDate(date);
    }

    @Override
    public Memo findMemoByNo(Long no) {
        return memoRepository.findMemoByMemoNo(no);
    }
}
