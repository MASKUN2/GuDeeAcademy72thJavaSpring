package com.maskun.projectdiary.core.configuration;

import com.maskun.projectdiary.core.TestAutoLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final TestAutoLogin testAutoLogin;

    /**
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testAutoLogin);
    }
}
