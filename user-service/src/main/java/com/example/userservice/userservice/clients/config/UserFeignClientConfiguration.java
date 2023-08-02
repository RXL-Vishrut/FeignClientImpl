package com.example.userservice.userservice.clients.config;

import com.example.userservice.userservice.clients.config.CustomErrorDecoder;
import com.example.userservice.userservice.service.JWTService;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFeignClientConfiguration {

    @Autowired
    JWTService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String jwtToken = jwtService.generateJwtToken();
            requestTemplate.header("Authorization", jwtToken);
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
