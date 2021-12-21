package com.example.demo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.example.demo.domain.Action;
import com.example.demo.domain.MoneyHistory;

import lombok.Getter;

@Getter
public class MoneyHistoryResponseDto {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Long id;
	private Long price;
	private Action action;
	private String transactionDt;
	private String description;
	
	private Long moneyId;
	private Long moneyPrice;
	private String moneyDesciption;
	
	private Long CategoryId;
	private String CategoryNm;
	
	
	
	public MoneyHistoryResponseDto(MoneyHistory moneyHistory) {
        this.id = moneyHistory.getId();
        this.price = moneyHistory.getPrice();
        this.action = moneyHistory.getAction();
        
        this.transactionDt = simpleDateFormat.format(moneyHistory.getTransactionDt());
        this.description = moneyHistory.getDescription();
        
        this.moneyId = moneyHistory.getMoney().getId();
        this.moneyPrice = moneyHistory.getMoney().getPrice();
        this.moneyDesciption = moneyHistory.getMoney().getDescription();
        
        this.CategoryId = moneyHistory.getMoney().getCategory().getId();
        this.CategoryNm = moneyHistory.getMoney().getCategory().getNm();
    }
}
