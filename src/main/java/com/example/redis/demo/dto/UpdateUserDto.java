package com.example.redis.demo.dto;


import com.example.redis.demo.model.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateUserDto {
    private String name;
    private Long id;
    private String email;

    public User toEntity (UpdateUserDto updateUserDto){
        return User.builder()
                .name(updateUserDto.getName())
                .id(updateUserDto.getId())
                .email(updateUserDto.getEmail())
                .build();
    }
}

