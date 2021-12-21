package com.example.demo.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.MoneyHistory;

public interface MoneyHistoryRepository extends JpaRepository<MoneyHistory,Long>, MoneyHistoryRepositoryCustom {

	
	

}

