package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.DinnerHistory;

public interface DinnerHistoryRepository extends JpaRepository<DinnerHistory,Long> {
	
	List<DinnerHistory> findAllByDateBetween(Date startDate, Date endDate);
	

}
