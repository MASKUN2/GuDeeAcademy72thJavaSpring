package com.maskun.projectdiary.core;

import com.maskun.projectdiary.domain.user.User;
import com.maskun.projectdiary.domain.user.UserService;
import com.maskun.projectdiary.web.dto.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <p>테스트를 위해 최초 1회 자동로그인을 수행</p>
 */
@RequiredArgsConstructor
@Component
public class TestAutoLogin implements HandlerInterceptor {
    private static int cnt = 1;
    private final UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(cnt == 1){
            User user = userService.login(new LoginDto("goodee", "1234"));
            request.getSession().setAttribute("loginUser", user);
            cnt --;
        }

        return true;
    }
}
