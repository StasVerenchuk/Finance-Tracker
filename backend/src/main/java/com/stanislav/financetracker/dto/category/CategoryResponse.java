package com.stanislav.financetracker.dto.category;

import com.stanislav.financetracker.entity.CategoryType;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategoryResponse
 * @since 2025/09/25 - 12.56
 */

public class CategoryResponse {

	private Long id;
	private String name;
	private String colorHex;
	private CategoryType type;
	private Long userId;
	
	// Constructors
	public CategoryResponse() {
		
	}
	
	public CategoryResponse(Long id, String name, String colorHex, CategoryType type, Long userId) {
		this.id = id;
		this.name = name;
		this.colorHex = colorHex;
		this.type = type;
		this.userId = userId;
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












