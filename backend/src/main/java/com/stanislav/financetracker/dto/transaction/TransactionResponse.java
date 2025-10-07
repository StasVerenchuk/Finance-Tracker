package com.stanislav.financetracker.dto.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.stanislav.financetracker.entity.CategoryType;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class TransactionResponse
 * @since 2025/09/30 - 14.26
 */

public class TransactionResponse {

	private Long id;
	private String name;
	private BigDecimal amount;
	private String description;
	private LocalDate date;
	private CategoryType type;
	private Long userId;
	private Long categoryId;
	
	// Constructors
	public TransactionResponse() {
		
	}
	
	public TransactionResponse(Long id, String name, BigDecimal amount,
			String description, LocalDate date, CategoryType type, Long userId, Long categoryId) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.type = type;
		this.userId = userId;
		this.categoryId = categoryId;
	}
	
	// Getters and Setters
	// Id
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	// Name
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}
		
	// Amount
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	// Description
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Date
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	// Category type
	public CategoryType getType() {
		return type;
	}
	
	public void setType(CategoryType type) {
		this.type = type;
	}
	
	// User id
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	// Category id
	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}