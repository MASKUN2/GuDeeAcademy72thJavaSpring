package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.annotation.LoginUser;
import com.maskun.projectdiary.service.MemoService;
import com.maskun.projectdiary.vo.domain.User;
import com.maskun.projectdiary.vo.domain.Memo;
import com.maskun.projectdiary.vo.dto.ResponseMessage;
import com.maskun.projectdiary.vo.dto.MemoAddDto;
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
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemoController {
    private final MemoService memoService;

    @GetMapping("/memo/{localDate}")
    public String getDateMemoList(@PathVariable LocalDate localDate, HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        List<Memo> memoList = memoService.getDateMemoList(localDate, user);
        model.addAttribute("memoList", memoList);
        model.addAttribute("date", localDate);
        model.addAttribute("yearMonth", YearMonth.from(localDate));
        return "memo/dateMemo";
    }

    @PostMapping("/memo/{localDate}")
    public ResponseEntity<ResponseMessage> addMemo(@Valid @RequestBody MemoAddDto memoAddDto, @LoginUser User user, Errors errors){
        log.debug("user={} , memoContent={}", user, memoAddDto);
        boolean isSuccess = memoService.addMemo(user, memoAddDto);
        log.debug("isSuccess : {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity<ResponseMessage>(HttpStatus.OK): new ResponseEntity<ResponseMessage>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/schedule/{memoNo}")
    public ResponseEntity modifyMemo(@PathVariable int memoNo, @RequestBody Map<String,String> memoMap){
        String memo = memoMap.get("memo");
        log.debug("받은 값 : memoNo:{}, memo:{}",memoNo, memoMap.get("memo"));
        boolean isSuccess = memoService.setMemo(memoNo, memo);
        log.debug("service.setDateSchedule result : {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/schedule/{memoNo}")
    public ResponseEntity removeMemo(@PathVariable int memoNo){
        log.debug("삭제요청 memoNo = {}",memoNo);
        boolean isSuccess = memoService.removeMemo(memoNo);
        log.debug(" ervice.delteDateSchedule result = {}",isSuccess);
        return (isSuccess)?
                new ResponseEntity(HttpStatus.OK): new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
