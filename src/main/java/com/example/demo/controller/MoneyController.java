package com.example.demo.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CommonResult;
import com.example.demo.common.ListResult;
import com.example.demo.common.SingleResult;
import com.example.demo.dto.MoneyRequestDto;
import com.example.demo.dto.MoneyResponseDto;
import com.example.demo.service.MoneyService;
import com.example.demo.service.ResponseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/money")
@Slf4j
public class MoneyController {
	
	private MoneyService moneyService;
	private ResponseService responseService;
	
	@GetMapping
	public ListResult<MoneyResponseDto> findByUserId(Principal principal) throws Exception {
		return responseService.getListResult(moneyService.findAllByUserId(Long.parseLong(principal.getName())));
	}
	
	@GetMapping("/{moneyId}")
	public SingleResult<MoneyResponseDto> findById(@PathVariable Long moneyId) throws Exception {
		return responseService.getSingleResult(moneyService.findById(moneyId));
	}
	
	@PostMapping
	public CommonResult create(Principal principal,@RequestBody MoneyRequestDto moneyRequestDto) throws Exception {
		moneyService.create(Long.parseLong(principal.getName()),moneyRequestDto);
		return responseService.getSuccessResult();
	}
	
	
	@DeleteMapping("/{moneyId}")
	public CommonResult delete(@PathVariable Long moneyId) throws Exception {
		moneyService.delete(moneyId);
		return responseService.getSuccessResult();
	}
	
	@PatchMapping("/{moneyId}")
	public CommonResult update(@PathVariable Long moneyId, @RequestBody MoneyRequestDto moneyRequestDto) throws Exception {
		moneyService.update(moneyId, moneyRequestDto);
		return responseService.getSuccessResult();
	}
	
}
