package com.maskun.projectdiary.service;

import com.maskun.projectdiary.web.dto.MyCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@RequiredArgsConstructor
@Service
public class CalendarService {

    public MyCalendar getCalendar(YearMonth yearMonth) {
    }
}
