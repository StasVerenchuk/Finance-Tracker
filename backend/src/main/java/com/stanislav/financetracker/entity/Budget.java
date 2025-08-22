package com.stanislav.financetracker.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * @class budget
 * @since 2025/08/08 - 17.10
 */

@Entity
@Table(name = "budgets")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	private int month;
	
	private int year;
	
	@Column(nullable = false)
	private BigDecimal amount;
	
	// Constructors
	public Budget() {
		
	}
	
	public Budget(Long id, User user, int month, int year, BigDecimal amount) {
		this.id = id;
		this.user = user;
		this.month = month;
		this.year = year;
		this.amount = amount;
	}
	
	// Accessors and mutators
	// Id
	public Long getId() {
		return id;
	}
	
	// User
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	// Month
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	// Year
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	// Amount
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
