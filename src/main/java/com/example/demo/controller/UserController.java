package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonResult;
import com.example.demo.common.ListResult;
import com.example.demo.common.SingleResult;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.ResponseService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/user/id/{userId}")
    public SingleResult<UserResponseDto> findUserById(
             @PathVariable Long userId,
            @RequestParam String lang) {
        return responseService.getSingleResult(userService.findById(userId));
    }
    
    @GetMapping("/user/email/{email}")
    public SingleResult<UserResponseDto> findUserByEmail
            (@PathVariable String email,
              @RequestParam String lang) {
        return responseService.getSingleResult(userService.findByEmail(email));
    }

    @GetMapping("/users")
    public ListResult<UserResponseDto> findAllUser() {
        return responseService.getListResult(userService.findAllUser());
    }

    @PutMapping("/user")
    public SingleResult<Long> update (
            @RequestParam Long userId,
            @RequestParam String nickName) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .nickName(nickName)
                .build();
        return responseService.getSingleResult(userService.update(userId, userRequestDto));
    }
    
    @DeleteMapping("/user/{userId}")
    public CommonResult delete(
            @PathVariable Long userId) {
        userService.delete(userId);
        return responseService.getSuccessResult();
    }
}