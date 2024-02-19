package com.maskun.projectdiary.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class CustomUsernamePasswordAuthenticationFilter {
    private final CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(){
        var filter = new UsernamePasswordAuthenticationFilter(customAuthenticationManager);
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/v2/user/login",
                "POST"));
        return filter;
    }

}
