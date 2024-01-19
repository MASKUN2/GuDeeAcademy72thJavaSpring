package com.maskun.projectdiary.web.dto;

import com.maskun.projectdiary.domain.memo.Memo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateVoTest {

    @Test
    void getThreeMemos() {
        DateVo dateVo = new DateVo(LocalDate.now());
        dateVo.addMemoOne(new Memo());
        dateVo.addMemoOne(new Memo());
        dateVo.addMemoOne(new Memo());
        dateVo.addMemoOne(new Memo());
        dateVo.addMemoOne(new Memo());
        System.out.println(dateVo.getThreeMemos().size());
        assertEquals(3, dateVo.getThreeMemos().size());

    }
}