package com.maskun.projectdiary.domain;

import com.maskun.projectdiary.externalApiRequest.HolidayApiJavaNetImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class HolidayApiJavaNetImplTest {
    @Autowired
    HolidayApiJavaNetImpl requester;


    @Test
    public void 휴일을가져온다() throws IOException {
        requester.getHolidayList("2023-05");

    }

}