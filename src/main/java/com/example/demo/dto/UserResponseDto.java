package com.example.demo.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.domain.User;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final String email;
    private final String name;
    private final String nickName;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;
    private final Date modifiedDate;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.roles = user.getRoles();
        this.authorities = user.getAuthorities();
        this.modifiedDate = user.getModifiedDt();
    }
}
