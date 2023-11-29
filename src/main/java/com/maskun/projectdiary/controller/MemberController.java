package com.maskun.projectdiary.controller;

import com.maskun.projectdiary.service.MemberService;
import com.maskun.projectdiary.vo.Member;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.WebSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member/")
public class MemberController {
    private final MemberService service;

    @GetMapping("login")
    public String getLoginPage(){
        return "member/login";
    }
    @PostMapping("login")
    public String doLogin(Member loginRequest, HttpSession session, Model model){
        boolean isSuccess = service.doLogin(loginRequest, session);
        if(isSuccess){
            model.addAttribute("serverMessage","Login Success");
            return "home";
        }else {
            model.addAttribute("serverMessage","Login fail");
            getLoginPage();
            return null;
        }
    }
}
