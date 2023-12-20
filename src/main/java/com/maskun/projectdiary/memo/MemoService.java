package com.maskun.projectdiary.memo;

import java.time.LocalDate;
import java.util.List;

public interface MemoService {

    List<Memo> findMemoList(LocalDate date);

    Memo findMemoByNo(Long no);
}
