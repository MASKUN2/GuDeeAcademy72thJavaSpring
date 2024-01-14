package com.maskun.projectdiary.core.webfilter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**전역 인코딩필터
 * */
@Slf4j
@Component
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("인코딩필터호출");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
