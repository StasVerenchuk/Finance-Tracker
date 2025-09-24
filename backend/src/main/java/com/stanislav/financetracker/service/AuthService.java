package com.stanislav.financetracker.service;

import com.stanislav.financetracker.dto.auth.AuthResponse;
import com.stanislav.financetracker.dto.auth.LoginRequest;
import com.stanislav.financetracker.dto.auth.RegisterRequest;

public interface AuthService {

	AuthResponse login(LoginRequest loginRequest);
	String register(RegisterRequest registerRequest);
	String logout(String token);
}
