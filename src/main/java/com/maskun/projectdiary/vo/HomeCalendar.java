package com.maskun.projectdiary.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.jasper.tagplugins.jstl.core.If;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeCalendar {
    private final YearMonth yearMonth;
    private final int year;
    private final int month;
    private final int lengthOfMonth;
    private final DayOfWeek dayOfWeekBeginningWeek = DayOfWeek.SUNDAY;
    private final DayOfWeek dayOfWeekFirstDayOfMonth;
    private final DayOfWeek dayOfWeekLastDayOfMonth;
    private final int numberOfFrontBlank;
    private final int numberOfBackBlank;
    private final List<DateInfo> dateInfoList;

    public HomeCalendar(YearMonth yearMonth){
        this.yearMonth = yearMonth;
        this.year = yearMonth.getYear();
        this.month = yearMonth.getMonthValue();
        this.lengthOfMonth = yearMonth.lengthOfMonth();
        this.dayOfWeekFirstDayOfMonth = LocalDate.of(this.year, this.month, 1).getDayOfWeek();
        this.dayOfWeekLastDayOfMonth = LocalDate.of(this.year, this.month, this.lengthOfMonth).getDayOfWeek();
        this.numberOfFrontBlank = this.dayOfWeekFirstDayOfMonth.getValue() % dayOfWeekBeginningWeek.getValue();
        this.numberOfBackBlank = dayOfWeekBeginningWeek.minus(1L).getValue() -(this.dayOfWeekLastDayOfMonth.getValue() % dayOfWeekBeginningWeek.getValue());
        this.dateInfoList = new ArrayList<>();
        for (int day = 1; day <= this.lengthOfMonth; day++){
            this.dateInfoList.add(new DateInfo(LocalDate.of(this.year, this.month, day)));
        }
    }

}
