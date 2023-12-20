package com.maskun.projectdiary.memo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class MemoServiceTest {

    @Autowired
    MemoService memoService;
    @Transactional
    @Test
    void findMemoList() {
        LocalDate date = LocalDate.parse("2024-05-30");
        List<Memo> memoList = memoService.findMemoList(date);
        memoList.forEach(System.out::println);

        Memo memo = memoService.findMemoByNo(626L);
        System.out.println(memo.toString());
    }
}