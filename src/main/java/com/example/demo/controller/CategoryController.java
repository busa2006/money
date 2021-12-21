package com.example.demo.controller;

import javax.validation.Valid;

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
import com.example.demo.dto.CategoryRequestDto;
import com.example.demo.dto.CategoryResponseDto;
import com.example.demo.dto.MoneyRequestDto;
import com.example.demo.dto.MoneyResponseDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.MoneyHistoryService;
import com.example.demo.service.ResponseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {
	
	private ResponseService responseService;
	private CategoryService categoryService;
	
	@GetMapping("/{categoryId}")
	public SingleResult<CategoryResponseDto> get(@PathVariable Long categoryId) throws Exception {
		return responseService.getSingleResult(categoryService.findById(categoryId));
	}
	
	@GetMapping
	public ListResult<CategoryResponseDto> getAll() throws Exception {
		return responseService.getListResult(categoryService.findAll());
	}
	
	@PostMapping
	public CommonResult create(@RequestBody @Valid CategoryRequestDto categoryRequest) throws Exception {
		categoryService.create(categoryRequest);
		return responseService.getSuccessResult();
	}
	
	@DeleteMapping("/{categoryId}")
	public CommonResult delete(@PathVariable Long categoryId) throws Exception {
		categoryService.delete(categoryId);
		return responseService.getSuccessResult();
	}
	
	@PatchMapping("/{categoryId}")
	public CommonResult update(@PathVariable Long categoryId, @RequestBody @Valid CategoryRequestDto categoryRequestDto) throws Exception {
		categoryService.update(categoryId, categoryRequestDto);
		return responseService.getSuccessResult();
	}
}
