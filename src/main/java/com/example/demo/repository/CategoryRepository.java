package com.example.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Category;
import com.example.demo.dto.MoneyRequestDto;
import com.example.demo.dto.MoneyResponseDto;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}

