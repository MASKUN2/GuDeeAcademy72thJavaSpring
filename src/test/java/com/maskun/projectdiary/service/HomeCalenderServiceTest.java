package com.maskun.projectdiary.service;

import com.maskun.projectdiary.vo.dto.HomeCalendar;
import com.maskun.projectdiary.vo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.YearMonth;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class HomeCalenderServiceTest {

    @MockBean
    Member member;
    @Autowired
    HomeCalenderService service;
    @Test
    void 원하는날짜의캘린더가정상적으로반환된다() {
        HomeCalendar homeCalendar = service.getHomeCalendar(YearMonth.parse("2023-05"), member);
        assertThat( homeCalendar.getDateInfoList().get(4).getDateName())
                .isEqualTo("어린이날");
    }
}