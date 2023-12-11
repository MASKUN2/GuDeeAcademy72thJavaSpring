package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.UserService;
import com.maskun.projectdiary.vo.domain.User;
import com.maskun.projectdiary.vo.dto.UserAddDto;
import com.maskun.projectdiary.vo.dto.UserLoginDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String doLogin(@ModelAttribute UserLoginDto userLoginDto, HttpSession httpSession){
        User userFound = userService.doLogin(userLoginDto);
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
        return "/user/AddForm";
    }
    @PostMapping("user/add")
    public String addMember(@ModelAttribute UserAddDto userAddDto){
        boolean isSuccess = userService.addUser(userAddDto);
        if(isSuccess){
        return "/home";
        }else {
            return "/user/add";
        }
    }

}
