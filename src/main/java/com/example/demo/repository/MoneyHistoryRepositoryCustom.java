package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.MoneyHistory;
import com.example.demo.domain.User;
import com.example.demo.dto.MoneyHistoryResponseDto;

public interface MoneyHistoryRepositoryCustom {
	
	List<MoneyHistory> findAllMoneyHistory(Long userId, Long categoryId, Date moneyHistoryPeriodStart, Date moneyHistoryPeriodEnd);
	
}

