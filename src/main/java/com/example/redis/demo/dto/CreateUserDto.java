package com.example.redis.demo.dto;


import com.example.redis.demo.model.User;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateUserDto {
    private String name;
    private String email;

    public User toEntity (CreateUserDto createUserDto){
        return User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .build();
    }
}

