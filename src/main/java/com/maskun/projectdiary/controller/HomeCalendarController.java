package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.HomeCalenderService;
import com.maskun.projectdiary.vo.dto.HomeCalendar;
import com.maskun.projectdiary.vo.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.YearMonth;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeCalendarController {
    private final HomeCalenderService homeCalenderService;

    /**
     * 홈으로 요청한 경우 현재 yearMonth 페이지로 이동
     */
    @GetMapping("/home")
    public String home(){
        return "redirect:/home/"+YearMonth.now().toString();
    }

    /**
     * 홈 캘린더 페이지로 요청된 Pathvariable에 따라 홈 캘린더를 요청합니다.
     */
    @GetMapping("/home/{yearMonth}")
    public String home(@PathVariable YearMonth yearMonth, HttpSession session, Model model){

        log.debug(" 입력된 yearMonth = {}", yearMonth.toString());
        Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
        HomeCalendar homeCalendar = homeCalenderService.getHomeCalendar(yearMonth, memberLoggedIn);
        model.addAttribute("homeCalendar", homeCalendar);

        return "home";
    }
}
