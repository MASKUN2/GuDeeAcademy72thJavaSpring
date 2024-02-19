package com.maskun.projectdiary.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = true)
public class ProjectSecurityConfig {
    private final UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return
        http
                .authorizeHttpRequests(a -> a.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilter(usernamePasswordAuthenticationFilter)
                .logout(
                        l -> l.logoutUrl("/api/v2/user/logout")
                                .logoutSuccessUrl("/")
                )
        .build();
    }
}
