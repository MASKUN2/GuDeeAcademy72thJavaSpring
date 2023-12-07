package com.maskun.projectdiary.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * 로컬에서 new 연산자로 선언된 RestTemplate은 StringHttpMessageConverter의 우선순위는 여기에서 선언된 것과 차이가 있었다.
 * 스프링 빈으로 생성될 경우 공공데이터의 xml response를 자동으로 json 으로 convert 해주었다. 이 점을 유의할 것.
 *
 * 만약 MappingJackson2HttpMessageConverter 가 MappingJackson2XmlHttpMessageConverter 보다 우선순위라면 이같은 일이 발생한다.
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .build();
    }
}
