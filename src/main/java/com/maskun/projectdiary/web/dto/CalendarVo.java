package com.maskun.projectdiary.web.dto;

import com.maskun.projectdiary.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import java.time.YearMonth;
import java.util.List;
@Getter
@ToString
public class CalendarVo {
    private final User user;
    private final YearMonth yearMonth;
    private final List<DateVo> dateVoList;

    public CalendarVo(User user, YearMonth yearMonth, List<DateVo> dateVoList) {
        this.user = user;
        this.yearMonth = yearMonth;
        this.dateVoList = dateVoList;
    }

    private void validateCalendarConstructor(){

    }
}
