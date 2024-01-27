package com.maskun.projectdiary.web;

import com.maskun.projectdiary.domain.user.DuplicateUserIdException;
import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.domain.user.UserService;
import com.maskun.projectdiary.web.dto.LoginDto;
import com.maskun.projectdiary.web.dto.UserRegisterReq;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.YearMonth;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    @PostMapping("/api/v1/user/login")
    @ResponseBody
    public ResponseEntity<Object> login(HttpSession session, LoginDto loginReq){
        User user = userService.login(loginReq);
        if(user == null){
            return badRequest().body("로그인정보가 일치하지 않습니다");
        }
        session.setAttribute("loginUser", user);
        return ok().build();
    }
    @GetMapping("/user/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/calendar/"+ YearMonth.now();
    }

    @GetMapping("/user/register")
    public String getUserRegisterPage(Model model){
        model.addAttribute("form", UserRegisterReq.empty());
        return "user/register";
    }

    @PostMapping("/user/register")
    @ResponseBody
    public ResponseEntity<Object> RegisterUser(@Validated @RequestBody UserRegisterReq req, Errors errors, Model model){
        if(errors.hasErrors()){
            String errorMessage = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("\n"));
            return badRequest().body(Map.of("serverMessage",errorMessage));
        }
        try {
            userService.registerUser(req);
        }catch (DuplicateUserIdException e){
            return badRequest().body("사용불가능한 아이디입니다");
        }catch (Exception e){
            return internalServerError().body("회원 가입에 실패했습니다. 관리자에게 문의하세요");
        }
        return ok().build();
    }

}
