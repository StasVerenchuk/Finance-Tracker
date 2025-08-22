package com.stanislav.financetracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class Category
 * @since 2025/08/06 - 13.41
 */

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String colorHex;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoryType type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	// Constructors
	public Category() {
		
	}
	
	public Category(Long id, String name, String colorHex, CategoryType type, User user) {
		this.id = id;
		this.name = name;
		this.colorHex = colorHex;
		this.type = type;
		this.user = user;
	}
	
	// Accessors and mutators
	// Id
	public Long getId() {
		return id;
	}
	
	// Category name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Hex color
	public String getColor() {
		return colorHex;
	}
	
	public void setColor(String colorHex) {
		this.colorHex = colorHex;
	}
	
	// Category type
	public CategoryType getType() {
		return type;
	}
	
	public void setType(CategoryType type) {
		this.type = type;
	}
	
	// User
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
