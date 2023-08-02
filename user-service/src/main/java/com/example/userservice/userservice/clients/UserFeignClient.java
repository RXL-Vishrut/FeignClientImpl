package com.example.userservice.userservice.clients;

import com.example.userservice.userservice.clients.config.UserFeignClientConfiguration;
import com.example.userservice.userservice.domain.UserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-service", url = "localhost:8000", configuration = UserFeignClientConfiguration.class)
public interface UserFeignClient {

    @GetMapping("/api/v1/user")
    UserDetails fetchUserDetails();
}
