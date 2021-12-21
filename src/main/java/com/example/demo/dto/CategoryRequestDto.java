package com.example.demo.dto;

import com.example.demo.domain.Category;

import lombok.Getter;

@Getter
public class CategoryRequestDto {
	private String nm;
	
	public Category toEntity(){
        return Category.builder()
                .nm(nm)
                .build();
    }
}
