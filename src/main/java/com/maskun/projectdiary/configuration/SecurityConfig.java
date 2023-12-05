package com.maskun.projectdiary.configuration;

import com.maskun.projectdiary.security.AdminAuthInterceptor;
import com.maskun.projectdiary.security.MemberAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    private  final String[] memberOnlyUrl = {"/member/logout"};
    private  final String[] adminOnlyUrl = {"/notice"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberAuthInterceptor())
                .order(1)
                .addPathPatterns(memberOnlyUrl);

        registry.addInterceptor(new AdminAuthInterceptor())
                .order(2)
                .addPathPatterns(adminOnlyUrl);
    }
}
