package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.SingleResult;
import com.example.demo.dto.TokenDto;
import com.example.demo.dto.TokenRequestDto;
import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.dto.UserLoginResponseDto;
import com.example.demo.dto.UserSignupRequestDto;
import com.example.demo.filter.JwtProvider;
import com.example.demo.service.ResponseService;
import com.example.demo.service.SignService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public SingleResult<TokenDto> login(
            @RequestBody UserLoginRequestDto userLoginRequestDto) {

        TokenDto tokenDto = signService.login(userLoginRequestDto);
        return responseService.getSingleResult(tokenDto);
    }

    @PostMapping("/signup")
    public SingleResult<UserLoginResponseDto> signup(
            @RequestBody UserSignupRequestDto userSignupRequestDto) {
        UserLoginResponseDto userLoginResponseDto = signService.signup(userSignupRequestDto);
        return responseService.getSingleResult(userLoginResponseDto);
    }
    
    @PostMapping("/reissue")
    public SingleResult<TokenDto> reissue(
            @RequestBody TokenRequestDto tokenRequestDto) {
        return responseService.getSingleResult(signService.reissue(tokenRequestDto));
    }
    
}