package com.maskun.projectdiary.vo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class HomeCalendarTest {
    @Test
    public void 날짜가_변환된다(){
        //givne
        String yearMonth = "2023-03";
        //when
        HomeCalendar homeCalendar = new HomeCalendar(yearMonth);
        //then
        int year = homeCalendar.getYearMonth().getYear();
        int month = homeCalendar.getYearMonth().getMonthValue();

        assertThat(year).isEqualTo(2023);
        assertThat(month).isEqualTo(3);

    }
    @Test
    public void 달의1일의요일번호가반환된다그리고마지막일자도(){
        //givne
        String yearMonth = "2023-11";
        int wednesday = 3;
        int lastDate = 30;
        //when
        HomeCalendar homeCalendar = new HomeCalendar(yearMonth);
        //then

        assertThat(homeCalendar.getWeekdayNameOfFirstDate()).isEqualTo(wednesday);
        assertThat(homeCalendar.getLengthOfMonth()).isEqualTo(lastDate);

    }
}