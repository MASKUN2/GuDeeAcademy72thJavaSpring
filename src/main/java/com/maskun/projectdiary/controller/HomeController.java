package com.maskun.projectdiary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
