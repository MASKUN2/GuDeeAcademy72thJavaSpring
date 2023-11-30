package com.maskun.projectdiary.controller;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.maskun.projectdiary.service.HomeService;
import com.maskun.projectdiary.vo.HomeCalendar;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HomeService service;
    @GetMapping("/home/{yearMonth}")
    public String home(@PathVariable String yearMonth, HttpSession session, Model model){
        try{
            YearMonth.parse(yearMonth);//The Right format "yyyy-MM"
        }catch (DateTimeParseException e){
            log.debug("YearMonth 파싱에 실패했습니다. 현재 날짜로 계속 진행합니다. 입력된 {yearMonth} : \"{}\"", yearMonth);
            return "redirect:/home/"+YearMonth.now().toString();
        }
        HomeCalendar homeCalendar = service.getCalendar(yearMonth, session);
        model.addAttribute("homeCalendar", homeCalendar);
        return "home";
    }
}
