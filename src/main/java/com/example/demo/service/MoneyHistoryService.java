package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Action;
import com.example.demo.domain.Category;
import com.example.demo.domain.Money;
import com.example.demo.domain.MoneyHistory;
import com.example.demo.domain.User;
import com.example.demo.dto.MoneyHistoryRequestDto;
import com.example.demo.dto.MoneyHistoryResponseDto;
import com.example.demo.dto.MoneyRequestDto;
import com.example.demo.dto.MoneyResponseDto;
import com.example.demo.exception.CCategoryNotFoundException;
import com.example.demo.exception.CMoneyHistoryNotFoundException;
import com.example.demo.exception.CMoneyNotFoundException;
import com.example.demo.exception.CUserNotFoundException;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MoneyHistoryRepository;
import com.example.demo.repository.MoneyRepository;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoneyHistoryService {
	private final MoneyHistoryRepository moneyHistoryRepository;
	
	public MoneyHistoryResponseDto findById(Long moneyHistoryId) throws Exception {
		MoneyHistory moneyHistory = moneyHistoryRepository.findById(moneyHistoryId).orElseThrow(CMoneyHistoryNotFoundException::new);
		return new MoneyHistoryResponseDto(moneyHistory);
	}
	
	public List<MoneyHistoryResponseDto> findAllMoneyHistory(String userId, Long categoryId, Date moneyHistoryPeriodStart, Date moneyHistoryPeriodEnd) {
		return moneyHistoryRepository.findAllMoneyHistory(Long.parseLong(userId) ,categoryId,moneyHistoryPeriodStart,moneyHistoryPeriodEnd).stream().map(p -> new MoneyHistoryResponseDto(p)).collect(Collectors.toList());

	}
}
