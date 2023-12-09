package com.maskun.projectdiary.service;

import com.maskun.projectdiary.vo.HomeCalendar;
import com.maskun.projectdiary.vo.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class HomeCalenderServiceTest {

    @MockBean
    Member member;
    @Autowired
    HomeCalenderService service;
    @Test
    void 원하는날짜의캘린더가정상적으로반환된다() {
        HomeCalendar homeCalendar = service.getHomeCalendar("2023-05", member);
        assertThat( homeCalendar.getDateInfoList().get(4).getDateName())
                .isEqualTo("어린이날");
    }
}