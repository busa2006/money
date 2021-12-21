package com.example.demo.dto;

import com.example.demo.domain.Category;
import com.example.demo.domain.Money;
import com.example.demo.domain.User;

import lombok.Getter;

@Getter
public class MoneyResponseDto {
	private Long id;
	private Long price;
	private Long categoryId;
	private	String categoryNm;
	private String description;
	
	public MoneyResponseDto(Money money) {
        this.id = money.getId();
        this.price = money.getPrice();
        this.categoryId = money.getCategory().getId();
        this.categoryNm = money.getCategory().getNm();
        this.description = money.getDescription();
    }
}
