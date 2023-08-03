package com.example.authservice.controller;

import com.example.authservice.rabbitMQ.Payload;
import com.example.authservice.rabbitMQ.RabbitMQSender;
import com.example.authservice.utils.JwtUtil;
import com.example.authservice.domain.User;
import com.example.authservice.userRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RabbitMQSender rabbitMQSender;

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

    @PostMapping("/v1/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = userRepo.save(user);
        Payload payload = new Payload(user.getId());
        rabbitMQSender.send(payload);
        return ResponseEntity.created(null).build();
    }
}
