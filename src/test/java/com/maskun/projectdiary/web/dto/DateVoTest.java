package com.maskun.projectdiary.web.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateVoTest {

    @Test
    void getThreeMemos() {
        DateVo dateVo = new DateVo(LocalDate.now());
        System.out.println(dateVo.getThreeMemos().size());
        assertEquals(3, dateVo.getThreeMemos().size());

    }
}