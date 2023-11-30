package com.maskun.projectdiary.service;

import com.maskun.projectdiary.vo.HomeCalendar;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class HomeServiceTest {

    @MockBean
    HttpSession session;
    @Autowired
    HomeService service;
    @Test
    void 원하는날짜의캘린더가정상적으로반환된다() {
        HomeCalendar homeCalendar = service.getCalendar("2023-05", session);
        assertThat( homeCalendar.getDateInfoList().get(4).getDateName())
                .isEqualTo("어린이날");
    }
}