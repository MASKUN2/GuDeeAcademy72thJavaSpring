package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.ScheduleService;
import com.maskun.projectdiary.vo.Member;
import com.maskun.projectdiary.vo.Memo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ScheduleController {
    private final ScheduleService service;

    @GetMapping("/schedule/{yearMonthDate}")
    public String getDateSchedule(@PathVariable String yearMonthDate, HttpSession session, Model model){
        try{
            LocalDate.parse(yearMonthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));//The Right format "yyyy-MM-dd"
        }catch (DateTimeParseException e){
            log.debug("yearMonthDate 파싱에 실패했습니다. 현재 날짜로 계속 진행합니다. 필요한 Right format \"yyyy-MM-dd\" , 입력된 {yearMonth} : \"{}\"", yearMonthDate);
            return "redirect:/home/"+YearMonth.now().toString();
        }
        List<Memo> memoList = service.getDateSchedule(yearMonthDate, session);
        model.addAttribute("memoList", memoList);
        model.addAttribute("yearMonthDate", yearMonthDate);
        model.addAttribute("yearMonth", yearMonthDate.substring(0, 7));
        return "schedule/date";
    }

    @PostMapping("/schedule")
    public ResponseEntity addMemo(@RequestBody Map<String,String> ReqBodyMap, HttpSession session){
        Member member = (Member) session.getAttribute("memberLoggedIn");
        String yearMonthDate = ReqBodyMap.get("yearMonthDate");
        String memo = ReqBodyMap.get("memo");
        log.debug("add date member={} , memo={}", member, memo);
        boolean isSuccess = service.insertDateSchedule(member.getMemberId(), yearMonthDate,memo);
        log.debug("service.insertDateSchedule result : {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/schedule/{memoNo}")
    public ResponseEntity modifyMemo(@PathVariable int memoNo, @RequestBody Map<String,String> memoMap){
        String memo = memoMap.get("memo");
        log.debug("받은 값 : memoNo:{}, memo:{}",memoNo, memoMap.get("memo"));
        boolean isSuccess = service.setDateSchedule(memoNo, memo);
        log.debug("service.setDateSchedule result : {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/schedule/{memoNo}")
    public ResponseEntity removeMemo(@PathVariable int memoNo){
        log.debug("삭제요청 memoNo = {}",memoNo);
        boolean isSuccess = service.delteDateSchedule(memoNo);
        log.debug(" ervice.delteDateSchedule result = {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
