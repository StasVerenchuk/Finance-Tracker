package com.stanislav.financetracker.dto.auth;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class AuthResponse
 * @since 2025/08/24 - 19.04
 */

public class AuthResponse {

	private String accessToken;
	private String refreshToken;
	
	// Constructors
	public AuthResponse() {
		
	}
	
	public AuthResponse(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	// Getters/Setters
	// Access Token
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	// Refresh Token
	public String getRefreshToken() {
		return refreshToken;
	}
	
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
