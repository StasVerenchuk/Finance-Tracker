package com.stanislav.financetracker.dto.category;

import com.stanislav.financetracker.entity.CategoryType;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategorySaveRequest
 * @since 2025/09/25 - 12.30
 */

public class CategorySaveRequest {

	private String name;
	private String colorHex;
	private CategoryType type;
	private Long userId;
	
	// Constructors
	private CategorySaveRequest() {
		
	}
	
	private CategorySaveRequest(String name, String colorHex, CategoryType type, Long userId) {
		this.name = name;
		this.colorHex = colorHex;
		this.type = type;
		this.userId = userId;
	}
	
	// Getters and Setters
	// Name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Color
	public String getColorHex() {
		return colorHex;
	}
	
	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}
	
	// Type
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
}
