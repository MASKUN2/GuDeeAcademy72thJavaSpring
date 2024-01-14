package com.maskun.projectdiary.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@RequiredArgsConstructor
@Getter
public class DateVo{
    private final LocalDate date;
    private String dateName;
    private Boolean isHoliday;

    public void setHoliday(final String dateName, final boolean isHoliday){
        this.dateName = dateName;
        this.isHoliday = isHoliday;
    }
}
