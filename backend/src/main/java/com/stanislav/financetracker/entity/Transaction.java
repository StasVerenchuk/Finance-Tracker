package com.stanislav.financetracker.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class Transaction
 * @since 2025/08/08 - 17.10
 */

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(nullable = false)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoryType type;
	
	private String description;
	
	@Column(nullable = false)
	private LocalDate date;
	
	// Constructors
	public Transaction() {
		
	}
	
	public Transaction(Long id, User user, Category category, BigDecimal amount, CategoryType type, String description, LocalDate date) {
		this.id = id;
		this.user = user;
		this.category = category;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.date = date;
	}
	
	// Accessors and mutators
	// Id
	public Long getId() {
		return id;
	}
	
	// Name
	public String getName() {
		return name;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	// User
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	// Category
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	// Transaction amount
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	// Category type
	public CategoryType getType() {
		return type;
	}
	
	public void setType(CategoryType type) {
		this.type = type;
	}
	
	//Description
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Date of transaction
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
