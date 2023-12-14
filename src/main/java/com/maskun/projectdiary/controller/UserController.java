package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.UserService;
import com.maskun.projectdiary.domain.entity.User;
import com.maskun.projectdiary.dto.UserAddRequestDto;
import com.maskun.projectdiary.dto.UserLoginRequestDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login")
    public String getLoginPage(){
        return "/user/login";
    }
    @PostMapping("/user/login")
    public String doLogin(String userId, String userPw, HttpSession httpSession){
        User userFound = userService.doLogin(userId, userPw);
        if(userFound != null){
            httpSession.setAttribute("loginUser", userFound);
            return "redirect:/home";
        }else {
            return "redirect:/user/login";
        }
    }
    @GetMapping("user/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/home";
    }
    @GetMapping("/user/add")
    public String getUserAddForm(){
        return "/user/addForm";
    }
    @PostMapping("user/add")
    public String addMember(@Valid @ModelAttribute UserAddRequestDto dto, Errors errors, BindingResult bindingResult){
        if (!dto.getPw().equals(dto.getPwCheck())){
            log.debug("비밀번호확인 불일치에러");
            bindingResult.addError(new FieldError("userAddDto", "pwCheck", "비밀번호 확인이 일치하지 않습니다."));
        }
        if(bindingResult.hasErrors()){
            log.debug("밸리데이션에러, 가입화면으로 리다이렉트됩니다.");
            return "/user/addForm";
        }
        boolean isSuccess = userService.addUser(dto.toServiceDto());
        return(isSuccess)? "redirect:/user/login":"/user/add";
    }

}
