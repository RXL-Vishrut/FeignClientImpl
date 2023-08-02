package com.example.authservice.authservice.controller.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

    private static final String SECRET_KEY = "550e8400-e29b-41d4-a716-446655440000";

    public static boolean validateJwt(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToken);

            Claims claims = claimsJws.getBody();

            long currentTimeMillis = System.currentTimeMillis();
            return !claims.getExpiration().before(new Date(currentTimeMillis));
        } catch (ExpiredJwtException ex) {
            log.error("The JWT Token is expired");
            return false;
        } catch (Exception ex) {
            log.error("Error occurred while validating JWT token");
            return false;
        }
    }
}
