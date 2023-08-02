package com.example.authservice.authservice.controller;

import com.example.authservice.authservice.controller.utils.JwtUtil;
import com.example.authservice.authservice.domain.User;
import com.example.authservice.authservice.userRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/v1/user")
    ResponseEntity<?> fetchUser() {
        String token = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest().getHeader("Authorization");
        boolean isTokenValid = !token.isEmpty() && JwtUtil.validateJwt(token);
        if (isTokenValid) {
            Optional<User> user = userRepo.findById(10001L);

            if (user.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(user.get());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
