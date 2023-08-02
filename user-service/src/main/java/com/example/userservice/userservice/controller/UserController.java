package com.example.userservice.userservice.controller;

import com.example.userservice.userservice.clients.UserFeignClient;
import com.example.userservice.userservice.domain.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/api/user")
    private UserDetails fetchUserDetails() {
        return userFeignClient.fetchUserDetails();
    }
}
