package com.example.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Money;
import com.example.demo.domain.User;
import com.example.demo.dto.MoneyResponseDto;

public interface MoneyRepository extends JpaRepository<Money,Long> {

	List<Money> findAllByUser(User user);

	

}
