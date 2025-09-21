package com.stanislav.financetracker.service;

import com.stanislav.financetracker.dto.auth.LoginRequest;

public interface AuthService {

	String login(LoginRequest loginRequest);
}
