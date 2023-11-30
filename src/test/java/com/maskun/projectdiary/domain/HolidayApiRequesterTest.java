package com.maskun.projectdiary.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HolidayApiRequesterTest {
    @Autowired
    HolidayApiRequester requester;


    @Test
    public void 휴일을가져온다() throws IOException {
        requester.getHoliday("2023-05");

    }

}