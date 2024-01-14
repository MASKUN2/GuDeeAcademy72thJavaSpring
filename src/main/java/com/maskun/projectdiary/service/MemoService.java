package com.maskun.projectdiary.service;

import com.maskun.projectdiary.domain.memo.Memo;

import java.time.LocalDate;
import java.util.List;

public interface MemoService {

    List<Memo> findMemoList(LocalDate date);

    Memo findMemoByNo(Long no);
}
