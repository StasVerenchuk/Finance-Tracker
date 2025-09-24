package com.stanislav.financetracker.dto.auth;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class AuthResponse
 * @since 2025/08/24 - 19.04
 */

public class AuthResponse {

	private Long id;
	private String email;
	private String accessToken;
	private String type = "Bearer";
	//private String refreshToken;
	
	// Constructors
	public AuthResponse() {
		
	}
	
	public AuthResponse(Long id, String email, String accessToken) {
		this.id = id;
		this.email = email;
		this.accessToken = accessToken;
	}
	
//	public AuthResponse(String accessToken, String refreshToken) {
//		this.accessToken = accessToken;
//		this.refreshToken = refreshToken;
//	}
	 
	
	// Getters/Setters
	// Id
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	// Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Access Token
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	//Token type
	public String getTokenType() {
		return type;
	}
	
	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}
	
	// Refresh Token
//	public String getRefreshToken() {
//		return refreshToken;
//	}
//	
//	public void setRefreshToken(String refreshToken) {
//		this.refreshToken = refreshToken;
//	}
	 
}
