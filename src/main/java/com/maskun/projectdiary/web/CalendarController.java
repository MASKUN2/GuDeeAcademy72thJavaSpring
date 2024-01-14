package com.maskun.projectdiary.web;

import com.maskun.projectdiary.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.YearMonth;
@RequiredArgsConstructor
@Controller
public class CalendarController {
    private final CalendarService calendarService;
    @GetMapping("/calendar/{yearMonth}")
    public String getCalendar(@PathVariable YearMonth yearMonth){
        return "home";
    }
}
