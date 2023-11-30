package com.maskun.projectdiary.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
public class HomeCalendar {
    private YearMonth yearMonth;
    private int year;
    private int month;
    private int lastDate;
    private int dayOfFirstDate;
    private int dayOfLastDate;
    private int numFrontBlank;
    private int numBackBlank;
    private List<DateInfo> dateInfoList;

    public HomeCalendar(String yearMonthStr){
        this.yearMonth = YearMonth.parse(yearMonthStr);
        this.year = yearMonth.getYear();
        this.month = yearMonth.getMonthValue();
        this.lastDate = yearMonth.lengthOfMonth();
        this.dayOfFirstDate = LocalDate.of(getYear(), getMonth(), 1).getDayOfWeek().getValue();
        this.dayOfLastDate = LocalDate.of(getYear(), getMonth(), this.lastDate).getDayOfWeek().getValue();
        this.numFrontBlank = this.dayOfFirstDate % 7;
        this.numBackBlank = 6 - (this.dayOfLastDate % 7);
        this.dateInfoList = new ArrayList<>();
        for (int i = 1; i <= this.lastDate ; i++){
            this.dateInfoList.add(new DateInfo(i));
        }
    }

}
