package com.example.authservice.authservice.userRepository;

import com.example.authservice.authservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
