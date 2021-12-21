package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.User;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final Long userId;
    private final List<String> roles;
    private final Date createdDate;

    public UserLoginResponseDto(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRoles();
        this.createdDate = user.getCreatedDt();
    }
}
