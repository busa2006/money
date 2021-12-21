package com.example.demo.repository;

import static com.example.demo.domain.QMoneyHistory.moneyHistory;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.MoneyHistory;
import com.example.demo.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoneyHistoryRepositoryImpl implements MoneyHistoryRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	@Override
	public List<MoneyHistory> findAllMoneyHistory(Long userId, Long categoryId, Date moneyHistoryPeriodStart, Date moneyHistoryPeriodEnd) {
		return queryFactory
				.selectFrom(moneyHistory)
				.where(moneyHistory.money.user.userId.eq(userId)
					   ,categoryId == null ? null : moneyHistory.money.category.id.eq(categoryId)
					   ,(moneyHistoryPeriodStart == null) || (moneyHistoryPeriodEnd == null) ? null : moneyHistory.transactionDt.between(moneyHistoryPeriodStart, moneyHistoryPeriodEnd))
				.fetch();
				
	}

}
