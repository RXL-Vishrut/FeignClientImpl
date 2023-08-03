package com.example.authservice.userRepository;

import com.example.authservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByCouponCode(String couponCode);
}
