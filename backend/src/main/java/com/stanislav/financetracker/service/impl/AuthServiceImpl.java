package com.stanislav.financetracker.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stanislav.financetracker.dto.auth.AuthResponse;
import com.stanislav.financetracker.dto.auth.LoginRequest;
import com.stanislav.financetracker.dto.auth.RegisterRequest;
import com.stanislav.financetracker.entity.User;
import com.stanislav.financetracker.repository.UserRepository;
import com.stanislav.financetracker.security.JwtTokenProvider;
import com.stanislav.financetracker.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthServiceImpl(@Lazy AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AuthResponse login(LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword()
		);
		Authentication authentication = authenticationManager.authenticate(authToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateJwtToken(authentication);
		
		User user = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		return new AuthResponse(user.getId(), user.getEmail(), token);
	}
	
	@Override
	public String register(RegisterRequest registerRequest) {
		if(userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new RuntimeException("Email is already in use");
		}
		
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setName(registerRequest.getName());
		user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCurrency(registerRequest.getCurrency());
		
		userRepository.save(user);
		
		return "User registered successfulyy!";
	}
	
	@Override
	public String logout(String token) {
		SecurityContextHolder.clearContext();
		
		return "User logged out successfully!";
	}
}
