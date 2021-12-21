package com.example.demo.dto;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String provider;
    private Long moneyPrice;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(nickName)
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .nickName(nickName)
                .name(name)
                .provider(provider)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}

