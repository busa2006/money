package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonResult;
import com.example.demo.common.ListResult;
import com.example.demo.common.SingleResult;
import com.example.demo.dto.MoneyHistoryRequestDto;
import com.example.demo.dto.MoneyHistoryResponseDto;
import com.example.demo.service.MoneyHistoryService;
import com.example.demo.service.ResponseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/moneyHistory")
@Slf4j
public class MoneyHistoryController {
	
	private MoneyHistoryService moneyHistoryService;
	private ResponseService responseService;
	
	@GetMapping("/{moneyHistoryId}")
	public SingleResult<MoneyHistoryResponseDto> get(@PathVariable Long moneyHistoryId) throws Exception {
		return responseService.getSingleResult(moneyHistoryService.findById(moneyHistoryId));
	}
	
	@GetMapping
	public ListResult<MoneyHistoryResponseDto> findAllMoneyHistory(Principal principal,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date moneyHistoryPeriodStart,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date moneyHistoryPeriodEnd,
			@RequestParam(required = false) Long categoryId
			) throws Exception {
		return responseService.getListResult(moneyHistoryService.findAllMoneyHistory
				(principal.getName(),categoryId,moneyHistoryPeriodStart,moneyHistoryPeriodEnd));
	}
	
}
