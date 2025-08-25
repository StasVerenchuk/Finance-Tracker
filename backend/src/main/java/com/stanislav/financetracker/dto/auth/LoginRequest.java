package com.stanislav.financetracker.dto.auth;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class LoginRequest
 * @since 2025/08/24 - 18.53
 */

public class LoginRequest {

	private String email;
	private String password;
	
	public LoginRequest() {
		
	}
	
	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	// Getters/Setters
	// Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Password
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
