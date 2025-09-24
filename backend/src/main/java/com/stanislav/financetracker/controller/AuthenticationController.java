package com.stanislav.financetracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanislav.financetracker.dto.auth.AuthResponse;
import com.stanislav.financetracker.dto.auth.LoginRequest;
import com.stanislav.financetracker.dto.auth.RegisterRequest;
import com.stanislav.financetracker.service.AuthService;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class AuthenticationController
 * @since 2025/09/23 - 15.43
 */

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	private final AuthService authService;
	
	public AuthenticationController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.login(loginRequest));
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
		String message = authService.register(registerRequest);
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String token){
		String message = authService.logout(token);
		
		return ResponseEntity.ok(message);
	}
}
