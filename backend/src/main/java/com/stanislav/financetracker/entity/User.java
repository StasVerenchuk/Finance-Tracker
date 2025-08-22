package com.stanislav.financetracker.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class User
 * @since 2025/08/08 - 12.11
 */

@Entity
@Table(name = "app_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Column(name = "name")
	private String name;
	
	private String currency;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Category> categories = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Budget> budgets = new ArrayList<>();
	
	// Constructors
	public User() {
		
	}
	
	public User(Long id, String email, String name, String passwordHash) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.passwordHash = passwordHash;
	}
	
	// Accessors and Mutators
	// Id
	public Long getId() {
		return id;
	}
	
	// Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	//User name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Password
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	// Currency
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	 // Created at
	public LocalDateTime getCreatedTime() {
		return createdAt;
	}
	
	// Collection of categories
	public List<Category> getCategories(){
		return categories;
	}
	
	// Collection of transactions
	public List<Transaction> getTransactions(){
		return transactions;
	}
	
	// Collection of budgets
	public List<Budget> getBudgets(){
		return budgets;
	}
}


























