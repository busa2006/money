package com.example.demo.aop;


import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.common.CommonResult;
import com.example.demo.exception.CAccessDeniedException;
import com.example.demo.exception.CAuthenticationEntryPointException;
import com.example.demo.exception.CCategoryNotFoundException;
import com.example.demo.exception.CCommunicationException;
import com.example.demo.exception.CEmailLoginFailedException;
import com.example.demo.exception.CEmailSignupFailedException;
import com.example.demo.exception.CExpiredAccessTokenException;
import com.example.demo.exception.CMoneyHistoryNotFoundException;
import com.example.demo.exception.CMoneyNotFoundException;
import com.example.demo.exception.CRefreshTokenException;
import com.example.demo.exception.CSocialAgreementException;
import com.example.demo.exception.CUserExistException;
import com.example.demo.exception.CUserNotFoundException;
import com.example.demo.service.ResponseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    /***
     * -9999
     * default Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        log.info(String.valueOf(e));
        return responseService.getFailResult
                (Integer.parseInt(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }

    /***
     * -1000
     * 유저를 찾지 못했을 때 발생시키는 예외
     */
    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("userNotFound.code")), getMessage("userNotFound.msg")
        );
    }

    /***
     * -1001
     * 유저 이메일 로그인 실패 시 발생시키는 예외
     */
    @ExceptionHandler(CEmailLoginFailedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected CommonResult emailLoginFailedException(HttpServletRequest request, CEmailLoginFailedException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("emailLoginFailed.code")), getMessage("emailLoginFailed.msg")
        );
    }

    /***
     * -1002
     * 회원 가입 시 이미 로그인 된 이메일인 경우 발생 시키는 예외
     */
    @ExceptionHandler(CEmailSignupFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult emailSignupFailedException(HttpServletRequest request, CEmailSignupFailedException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("emailSignupFailed.code")), getMessage("emailSignupFailed.msg")
        );
    }

    /**
     * -1003
     * 전달한 Jwt 이 정상적이지 않은 경우 발생 시키는 예외
     */
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult authenticationEntrypointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("authenticationEntrypoint.code")), getMessage("authenticationEntrypoint.msg")
        );
    }

    /**
     * -1004
     * 권한이 없는 리소스를 요청한 경우 발생 시키는 예외
     */
    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult accessDeniedException(HttpServletRequest request, CAccessDeniedException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("accessDenied.code")), getMessage("accessDenied.msg")
        );
    }

    /**
     * -1005
     * refresh token 에러시 발생 시키는 에러
     */
    @ExceptionHandler(CRefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult refreshTokenException(HttpServletRequest request, CRefreshTokenException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("refreshTokenInValid.code")), getMessage("refreshTokenInValid.msg")
        );
    }

    /**
     * -1006
     * 액세스 토큰 만료시 발생하는 에러
     */
    @ExceptionHandler(CExpiredAccessTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected CommonResult expiredAccessTokenException(HttpServletRequest request, CExpiredAccessTokenException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("expiredAccessToken.code")), getMessage("expiredAccessToken.msg")
        );
    }

    /***
     * -1007
     * Social 인증 과정에서 문제 발생하는 에러
     */
    @ExceptionHandler(CCommunicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult communicationException(HttpServletRequest request, CCommunicationException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("communicationException.code")), getMessage("communicationException.msg")
        );
    }

    /***
     * -1008
     * 기 가입자 에러
     */
    @ExceptionHandler(CUserExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected CommonResult existUserException(HttpServletRequest request, CUserExistException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("userExistException.code")), getMessage("userExistException.msg")
        );
    }

    /***
     * -1009
     * 소셜 로그인 시 필수 동의항목 미동의시 에러
     */
    @ExceptionHandler(CSocialAgreementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult socialAgreementException(HttpServletRequest request, CSocialAgreementException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("agreementException.code")), getMessage("agreementException.msg")
        );
    }

    /***
     * -1010
     * 종목을 찾지 못했을 때 발생시키는 예외
     */
    @ExceptionHandler(CMoneyNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult moneyNotFoundException(HttpServletRequest request, CMoneyNotFoundException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("moneyNotFound.code")), getMessage("moneyNotFound.msg")
        );
    }
    
    /***
     * -1011
     * 카테고리를 찾지 못했을 때 발생시키는 예외
     */
    @ExceptionHandler(CCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult categoryNotFoundException(HttpServletRequest request, CCategoryNotFoundException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("categoryNotFound.code")), getMessage("categoryNotFound.msg")
        );
    }
    
    /***
     * -1012
     * 종목기록을 찾지 못했을 때 발생시키는 예외
     */
    @ExceptionHandler(CMoneyHistoryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult moneyHistoryNotFoundException(HttpServletRequest request, CMoneyHistoryNotFoundException e) {
        return responseService.getFailResult(
                Integer.parseInt(getMessage("moneyHistoryNotFound.code")), getMessage("moneyHistoryNotFound.msg")
        );
    }
    

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
    
	
	
}
