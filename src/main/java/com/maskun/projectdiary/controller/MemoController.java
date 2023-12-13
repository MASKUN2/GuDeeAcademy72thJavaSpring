package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.annotation.LoginUser;
import com.maskun.projectdiary.service.MemoService;
import com.maskun.projectdiary.domain.entity.User;
import com.maskun.projectdiary.domain.entity.Memo;
import com.maskun.projectdiary.dto.MemoSetRequestDto;
import com.maskun.projectdiary.dto.ResponseMessage;
import com.maskun.projectdiary.dto.MemoAddRequestDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemoController {
    private final MemoService memoService;

    @GetMapping("/memo/{date}")
    public String getDateMemoList(@PathVariable LocalDate date, @LoginUser User user, Model model){
        String userId = user.getUserId();
        List<Memo> memoList = memoService.getDateMemoList(date,userId);
        model.addAttribute("memoList", memoList);
        model.addAttribute("date", date);
        model.addAttribute("yearMonth", YearMonth.from(date));
        return "memo/dateMemo";
    }

    @PostMapping("/memo/{localDate}")
    public ResponseEntity<ResponseMessage> addMemo(@Valid @RequestBody MemoAddRequestDto dto, @LoginUser User user, Errors errors){
        log.debug("dto = {}", dto);
        boolean isSuccess = memoService.addMemo(user, dto);
        log.debug("isSuccess : {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity<ResponseMessage>(HttpStatus.OK): new ResponseEntity<ResponseMessage>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /*
    @PutMapping("/schedule/{memoNo}")
    public ResponseEntity setMemo(@PathVariable int memoNo, @RequestBody MemoSetRequestDto memoSetDto){
        String memo = memoMap.get("memo");
        log.debug("받은 값 : memoNo:{}, memo:{}",memoNo, memoMap.get("memo"));
        boolean isSuccess = memoService.setMemo(memoNo, memo);
        log.debug("service.setDateSchedule result : {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    */
    @DeleteMapping("/schedule/{memoNo}")
    public ResponseEntity removeMemo(@PathVariable int memoNo){
        log.debug("삭제요청 memoNo = {}",memoNo);
        boolean isSuccess = memoService.removeMemo(memoNo);
        log.debug(" ervice.delteDateSchedule result = {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
