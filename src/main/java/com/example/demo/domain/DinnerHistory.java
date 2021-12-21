package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="dinner_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DinnerHistory {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String name;
	private String shop;
	private Date insert_dt = new Date(); 
	private Date date = new Date(); 
	
	public DinnerHistory(String name) {
		this.name = name;
	}
	
}
