package com.example.demo.domain;

import java.io.Serializable;

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
@Entity(name="year_asset_statistics")
@NoArgsConstructor
public class yearAssetStatistics implements Serializable {
	
	@Id 
	private String year;
	@Id
	private Long grade;
	private Long upperLimit;
	private Long lowerLimit;
	
}
