package com.stanislav.financetracker.dto.auth;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class RegisterRequest
 * @since 2025/08/23 - 21.06
 */

public class RegisterRequest {

	private String email;
	private String name;
	private String password;
	private String currency;
	
	// Constructors
	public RegisterRequest() {
		
	}
	
	public RegisterRequest(String email, String name, String password, String currency) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.currency = currency;
	}
	
	// Getters/Setters
	// Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Password
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Currency
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
