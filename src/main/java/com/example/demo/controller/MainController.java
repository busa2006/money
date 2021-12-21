package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.DinnerHistory;
import com.example.demo.repository.DinnerHistoryRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MainController {
	
	private DinnerHistoryRepository dinnerHistoryRepository;
	
	
	@GetMapping("/save")
	public DinnerHistory save(@RequestParam(required = false) String name) {
		DinnerHistory dinnerHistory = new DinnerHistory(name);
		dinnerHistoryRepository.save(dinnerHistory);
		return dinnerHistory;
	}
	
	@GetMapping("/list")
	public List<DinnerHistory> getList(@RequestParam(required = false) String year, @RequestParam(required = false) String month) {
		
		int yearDate = LocalDate.now().getYear();
		int monthDate = LocalDate.now().getMonthValue();
		
		if(year != null ) {
			yearDate = Integer.parseInt(year);
		}
		
		if(month != null) {
			monthDate = Integer.parseInt(month);
		}
		
		LocalDateTime startDatetime = getLocalDate(yearDate, monthDate, 1, LocalTime.of(0,0,0));
		LocalDateTime endDatetime = getLocalDate(yearDate, monthDate, startDatetime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(), LocalTime.of(23,59,59)); 
		Date startDate = java.sql.Timestamp.valueOf(startDatetime);
		Date endDate = java.sql.Timestamp.valueOf(endDatetime);
		return dinnerHistoryRepository.findAllByDateBetween(startDate,endDate);
	}
	
	
	public LocalDateTime getLocalDate(int year,int month,int day,LocalTime time) {
		return LocalDateTime.of(LocalDate.of(year, month, day), time);
	}
	
	
}
