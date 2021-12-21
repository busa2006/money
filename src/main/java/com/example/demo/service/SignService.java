package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Action;
import com.example.demo.domain.Category;
import com.example.demo.domain.Money;
import com.example.demo.domain.RefreshToken;
import com.example.demo.domain.User;
import com.example.demo.dto.MoneyRequestDto;
import com.example.demo.dto.TokenDto;
import com.example.demo.dto.TokenRequestDto;
import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.dto.UserLoginResponseDto;
import com.example.demo.dto.UserSignupRequestDto;
import com.example.demo.exception.CCategoryNotFoundException;
import com.example.demo.exception.CEmailLoginFailedException;
import com.example.demo.exception.CEmailSignupFailedException;
import com.example.demo.exception.CRefreshTokenException;
import com.example.demo.exception.CUserExistException;
import com.example.demo.exception.CUserNotFoundException;
import com.example.demo.filter.JwtProvider;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.RefreshTokenJpaRepo;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenJpaRepo tokenJpaRepo;
    private final MoneyService moneyService;
    private final CategoryRepository categoryRepository;
    
    @Transactional
    public TokenDto login(UserLoginRequestDto userLoginRequestDto) {

        // 회원 정보 존재하는지 확인
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(CEmailLoginFailedException::new);

        // 회원 패스워드 일치 여부 확인
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
            throw new CEmailLoginFailedException();

        // AccessToken, RefreshToken 발급
        TokenDto tokenDto = jwtProvider.createTokenDto(user.getUserId(), user.getRoles());

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .keyId(user.getUserId())
                .token(tokenDto.getRefreshToken())
                .build();
        tokenJpaRepo.save(refreshToken);
        return tokenDto;
    }

    @Transactional
    public UserLoginResponseDto signup(UserSignupRequestDto userSignupDto) {
        if (userRepository.findByEmail(userSignupDto.getEmail()).isPresent())
            throw new CEmailSignupFailedException();
        
        User user = userRepository.save(userSignupDto.toEntity(passwordEncoder));
        Category category = categoryRepository.findById(1L).orElseGet(()-> categoryRepository.save( Category.builder().nm("현금").build()) );	
        MoneyRequestDto moneyRequestDto = MoneyRequestDto.builder().action(Action.CREATE).price(userSignupDto.getMoneyPrice()).categoryId(category.getId()).build();
        moneyService.create(user.getUserId(), moneyRequestDto);
        
        return new UserLoginResponseDto(user);
    }

    @Transactional
    public Long socialSignup(UserSignupRequestDto userSignupRequestDto) {
        if (userRepository
                .findByEmailAndProvider(userSignupRequestDto.getEmail(), userSignupRequestDto.getProvider())
                .isPresent()
        ) throw new CUserExistException();
        return userRepository.save(userSignupRequestDto.toEntity()).getUserId();
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 만료된 refresh token 에러
        if (!jwtProvider.validationToken(tokenRequestDto.getRefreshToken())) {
            throw new CRefreshTokenException();
        }

        // AccessToken 에서 Username (pk) 가져오기
        String accessToken = tokenRequestDto.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        // user pk로 유저 검색 / repo 에 저장된 Refresh Token 이 없음
        User user = userRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(CUserNotFoundException::new);
        RefreshToken refreshToken = tokenJpaRepo.findByKeyId(user.getUserId())
                .orElseThrow(CRefreshTokenException::new);

        // 리프레시 토큰 불일치 에러
        if (!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken()))
            throw new CRefreshTokenException();

        // AccessToken, RefreshToken 토큰 재발급, 리프레쉬 토큰 저장
        TokenDto newCreatedToken = jwtProvider.createTokenDto(user.getUserId(), user.getRoles());
        RefreshToken updateRefreshToken = refreshToken.updateToken(newCreatedToken.getRefreshToken());
        tokenJpaRepo.save(updateRefreshToken);

        return newCreatedToken;
    }


}
