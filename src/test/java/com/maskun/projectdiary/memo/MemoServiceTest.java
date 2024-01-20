package com.maskun.projectdiary.memo;

import com.maskun.projectdiary.domain.memo.Memo;
import com.maskun.projectdiary.domain.memo.MemoRepository;
import com.maskun.projectdiary.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@SpringBootTest
class MemoServiceTest {

    @Autowired
    MemoService memoService;

    @Autowired
    MemoRepository memoRepository;
    @Transactional
    @Test
    void findMemoList() {
        LocalDate date = LocalDate.parse("2024-05-30");
        List<Memo> memoList = memoService.findMemoList(date);
        memoList.forEach(System.out::println);

        Memo memo = memoService.findMemoByNo(1000L);
        System.out.println(memo.toString());
        List<Memo> memoList1 = memoRepository.findByMemoDateBetweenAndUserId(LocalDate.parse("2023-01-03"), LocalDate.parse("2023-12-04"), "goodee");
        System.out.println(memoList1.size());
        memoList1.forEach(m -> log.info(m.toString()));
    }
}