package com.maskun.projectdiary.externalApiRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class HolidayApiTest {
    @Autowired
    private HolidayApi holidayApi;

    @Test
    void getHolidayList() throws IOException {
        //given
        String yearMonthMay = "2023-05";
        YearMonth yearMonth = YearMonth.parse(yearMonthMay);
        //when
        List<HolidayApiVo> holidayList = holidayApi.getHolidayList(yearMonth);
        //then
        assertThat(holidayList.size()).isEqualTo(3);
    }
}