package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.dto.CategoryRequestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity(name="category")
@NoArgsConstructor
public class Category {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "category_id")
	private Long id;
	private String nm;
	
	@Builder
    public Category(String nm) {
		this.nm = nm;
    }
	
	public void update(CategoryRequestDto categoryRequestDto) {
        this.nm = categoryRequestDto.getNm();
    }
}
