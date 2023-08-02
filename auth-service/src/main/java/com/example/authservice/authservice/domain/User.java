package com.example.authservice.authservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @JsonIgnore
    Long id;
    String firstname;
    String lastname;
}
