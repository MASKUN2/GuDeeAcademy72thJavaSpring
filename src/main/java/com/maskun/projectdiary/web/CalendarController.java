package com.maskun.projectdiary.web;

import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.service.CalendarService;
import com.maskun.projectdiary.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.YearMonth;
@RequiredArgsConstructor
@Controller
public class CalendarController {
    private final CalendarService calendarService;
    @GetMapping("/calendar/{yearMonth}")
    public String getCalendar(@PathVariable YearMonth yearMonth ,
                              @SessionAttribute(name = "loginUser", required = false)User user,
                              Model model){
        model.addAttribute("calendar", calendarService.getCalendar(yearMonth, user));
        model.addAttribute("loginDto", new LoginDto(null, null));
        return "calendar";
    }

    @GetMapping("/")
    public String getHome(){
        return "redirect:/calendar/"+ YearMonth.now();
    }
}
