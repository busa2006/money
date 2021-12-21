package com.example.demo.dto;

import java.util.Date;

import com.example.demo.domain.Action;
import com.example.demo.domain.Category;
import com.example.demo.domain.Money;
import com.example.demo.domain.MoneyHistory;
import com.example.demo.domain.User;

import lombok.Getter;

@Getter
public class MoneyHistoryRequestDto {
	
	private Long price;
	private Action action;
	private Date transactionDt;
	private Long moneyId;
	private String description;
	
	public MoneyHistory toEntity(Money money){
		
		return MoneyHistory.builder().price(price)
				.action(action)
				.transactionDt(transactionDt)
				.money(money)
				.description(description)
				.build();
    }
}
