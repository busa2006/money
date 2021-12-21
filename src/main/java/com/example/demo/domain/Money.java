package com.example.demo.domain;

import java.util.Date;
import java.util.Optional;

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

import org.hibernate.annotations.ColumnDefault;

import com.example.demo.common.BaseTimeEntity;
import com.example.demo.dto.CategoryRequestDto;
import com.example.demo.dto.MoneyHistoryRequestDto;
import com.example.demo.dto.MoneyRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="money")
@NoArgsConstructor
public class Money extends BaseTimeEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "money_id")
	private Long id;
	@ColumnDefault("0") 
	private Long price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Builder
    public Money(Long price,User user,String description,Category category) {
		this.price = price;
		this.user = user;
		this.description = description;
		this.category = category;
    }
	
	public void update(MoneyRequestDto moneyRequestDto) {
        
        switch(moneyRequestDto.getAction()) {
		case ADD :
			this.price = price + moneyRequestDto.getPrice();
			break;
		default :
			this.price = moneyRequestDto.getPrice();
			break;
		}
        
        this.description = moneyRequestDto.getDescription();
        
    }

	public MoneyHistory toHistoryEntity(MoneyRequestDto moneyRequestDto) {
		return MoneyHistory.builder().money(this)
				.action(moneyRequestDto.getAction())
				.transactionDt(new Date())
				.price(Optional.ofNullable(moneyRequestDto.getPrice()).orElse(0L))
				.description(moneyRequestDto.getDescription()).build();
				
	}
}
