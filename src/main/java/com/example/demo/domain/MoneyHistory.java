package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.common.BaseTimeEntity;
import com.example.demo.dto.MoneyHistoryRequestDto;
import com.example.demo.dto.MoneyRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="money_history")
@NoArgsConstructor
public class MoneyHistory extends BaseTimeEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "money_history_id")
	private Long id;
	private Long price;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "money_id")
	private Money money;
	private String description;
	private Date transactionDt;
	@Enumerated(EnumType.STRING)
	private Action action;
	
	@Builder
    public MoneyHistory(Long price,Money money,String description,Date transactionDt,Action action) {
		this.price = price;
		this.money = money;
		this.description = description;
		this.transactionDt = transactionDt;
		this.action = action;
    }

}
