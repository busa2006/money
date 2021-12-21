package com.example.demo.dto;

import com.example.demo.domain.Category;

import lombok.Getter;

@Getter
public class CategoryResponseDto {
	private Long id;
	private String nm;
	
	public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.nm = category.getNm();
    }
}
