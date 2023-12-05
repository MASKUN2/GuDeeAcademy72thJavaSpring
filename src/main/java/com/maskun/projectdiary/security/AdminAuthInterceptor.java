package com.maskun.projectdiary.security;

import com.maskun.projectdiary.vo.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 관리자의 (memeberLevel) 로그인 상태를 확인합니다.
 */
@Slf4j
public class AdminAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("인터셉터호출");
        String requestMethod = request.getMethod();
        if(requestMethod.equals("GET")){
            return true;
        }
        Member memberLoggedIn = (Member) request.getSession().getAttribute("memberLoggedIn");
        if(memberLoggedIn.getMemberLevel() == 1){
            return true;
        }else {
            response.sendRedirect("/diary/member/login");
            log.debug("관리자 레벨1 인증실패, 홈페이지 리다이렉트");
            return false;
        }
    }
}