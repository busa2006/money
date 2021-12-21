package com.example.demo.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Action;
import com.example.demo.domain.Category;
import com.example.demo.domain.Money;
import com.example.demo.domain.MoneyHistory;
import com.example.demo.domain.User;
import com.example.demo.dto.MoneyRequestDto;
import com.example.demo.dto.MoneyResponseDto;
import com.example.demo.exception.CCategoryNotFoundException;
import com.example.demo.exception.CMoneyNotFoundException;
import com.example.demo.exception.CUserNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MoneyHistoryRepository;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MoneyService {
	
	private final MoneyRepository moneyRepository;
	private final MoneyHistoryRepository moneyHistoryRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	
	public List<MoneyResponseDto> findAllByUserId(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(CUserNotFoundException::new);
		List<Money> moneyList = moneyRepository.findAllByUser(user);
		return moneyList.stream().map(p -> new MoneyResponseDto(p)).collect(Collectors.toList());
	}
	
	public MoneyResponseDto findById(Long moneyId) {
		Money money = moneyRepository.findById(moneyId).orElseThrow(CMoneyNotFoundException::new);
		MoneyResponseDto moneyResponseDto= new MoneyResponseDto(money);
		return moneyResponseDto;
	}

	public void create(Long userId, MoneyRequestDto moneyRequestDto) {
		Category category = categoryRepository.findById(moneyRequestDto.getCategoryId()).orElseThrow(CCategoryNotFoundException::new);	
		User user = userRepository.findById(userId).orElseThrow(CUserNotFoundException::new);
		Money money = moneyRequestDto.toEntity(category, user);
		MoneyHistory moneyHisotry = money.toHistoryEntity(moneyRequestDto);
		moneyHistoryRepository.save(moneyHisotry);
	}

	public void delete(Long moneyId) {
		Money money = moneyRepository.findById(moneyId).orElseThrow(CMoneyNotFoundException::new);
		moneyRepository.delete(money);
	}
	
	public void update(Long moneyId, MoneyRequestDto moneyRequestDto) {
		Money money = moneyRepository.findById(moneyId).orElseThrow(CMoneyNotFoundException::new);
		money.update(moneyRequestDto);
		MoneyHistory moneyHisotry = money.toHistoryEntity(moneyRequestDto);
		moneyHistoryRepository.save(moneyHisotry);
	}

}
