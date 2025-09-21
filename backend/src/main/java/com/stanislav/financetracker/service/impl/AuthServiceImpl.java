package com.stanislav.financetracker.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stanislav.financetracker.dto.auth.LoginRequest;
import com.stanislav.financetracker.security.JwtTokenProvider;
import com.stanislav.financetracker.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	
	public AuthServiceImpl() {
		
	}
	
	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword()
		));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}
	
	
}
