package com.example.userservice.userservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTService {
    public String generateJwtToken() {
        String secretKey = "550e8400-e29b-41d4-a716-446655440000";
        long expirationTimeInMilliseconds = 10000;

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeInMilliseconds);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
