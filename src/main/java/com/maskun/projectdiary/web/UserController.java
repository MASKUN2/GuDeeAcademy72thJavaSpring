package com.maskun.projectdiary.web;

import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.domain.user.UserService;
import com.maskun.projectdiary.web.dto.LoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.YearMonth;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @PostMapping("/api/v1/user/login")
    @ResponseBody
    public ResponseEntity<Object> login(HttpSession session, LoginDto loginReq){
        User user = userService.login(loginReq);
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        session.setAttribute("loginUser", user);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/calendar/"+ YearMonth.now();
    }

}
