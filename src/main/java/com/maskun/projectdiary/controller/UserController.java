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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
    public String addMember(@Valid @ModelAttribute UserAddRequestDto userAddDto, Errors errors, Model model){
        if(errors.hasErrors()){
            log.debug("밸리데이션에러");
            Map<String,String> fieldErrorMap = errors.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("fieldErrorMap",fieldErrorMap);
            return "/user/addForm";
        }else if (!userAddDto.getPw().equals(userAddDto.getPwCheck())){

        }
        boolean isSuccess = userService.addUser(userAddDto);
        if(isSuccess){
        return "/home";
        }else {
            return "/user/add";
        }
    }

}
