package com.stanislav.financetracker.mapper;

import org.springframework.stereotype.Component;

import com.stanislav.financetracker.dto.auth.AuthResponse;
import com.stanislav.financetracker.dto.auth.RegisterRequest;
import com.stanislav.financetracker.entity.User;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class UserMapper
 * @since 2025/08/2 - 12.42
 */

@Component
public class UserMapper {

	// Mapping RegisterRequest -> User
	public User toUser(RegisterRequest request, String encodedPassword) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPasswordHash(encodedPassword);
		user.setCurrency(request.getCurrency());
		
		return user;
	}
	
	// Mapping User -> AuthResponse
//	public AuthResponse toAuthResponse(String accessToken) {
//		AuthResponse response = new AuthResponse(accessToken);
//		
//		return response;
//	}
	
//	public AuthResponse toAuthResponse(String accessToken, String refreshToken) {
//		AuthResponse response = new AuthResponse(accessToken, refreshToken);
//		
//		return response;
//	}
}
