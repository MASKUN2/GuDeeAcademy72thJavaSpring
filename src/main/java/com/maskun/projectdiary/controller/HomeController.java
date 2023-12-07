package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.HomeService;
import com.maskun.projectdiary.vo.HomeCalendar;
import com.maskun.projectdiary.vo.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HomeService homeService;

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
    public String home(@PathVariable String yearMonth, HttpSession session, Model model){
        try{
            YearMonth.parse(yearMonth);//The Right format "yyyy-MM"
        }catch (DateTimeParseException e){
            log.debug("YearMonth 파싱에 실패했습니다. 현재 날짜로 계속 진행합니다. 입력된 yearMonth = {}", yearMonth);
            return "redirect:/home/"+YearMonth.now().toString();
        }

        Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");

        HomeCalendar homeCalendar = homeService.getCalendar(yearMonth, memberLoggedIn);

        model.addAttribute("homeCalendar", homeCalendar);

        //이번 달의 현재날짜에 대한 마커
        if(yearMonth.equals(YearMonth.now().toString())) {
            int todayMarker = LocalDate.now().getDayOfMonth();
            model.addAttribute("todayMarker", todayMarker);
        }
        return "home";
    }
}
