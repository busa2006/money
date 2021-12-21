package com.example.demo.dto;

import java.util.Date;
import java.util.Optional;

import com.example.demo.domain.Action;
import com.example.demo.domain.Category;
import com.example.demo.domain.Money;
import com.example.demo.domain.MoneyHistory;
import com.example.demo.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoneyRequestDto {
	
	private Action action;
	private Long price;
	private Long categoryId;
	private String description;
	
	public Money toEntity(Category category, User user){
		return Money.builder().price(Optional.ofNullable(price).orElse(0L)).description(description)
				.category(category).user(user).build();
    }
}
