package com.stanislav.financetracker.security;

import com.stanislav.financetracker.controller.AuthenticationController;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationController authenticationController;

	private final CustomUserDetailsService userDetailsService;
	
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	private final JwtAuthenticationFilter authenticationFilter;
	
	
	public SecurityConfig(CustomUserDetailsService userDetailsService,
			JwtAuthenticationEntryPoint authenticationEntryPoint, @Lazy JwtAuthenticationFilter authenticationFilter, AuthenticationController authenticationController) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
		this.authenticationController = authenticationController;
	}
	
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        	.csrf(csrf -> csrf.disable())
        	.cors(Customizer.withDefaults()) // Дозволяємо CORS
        	.authorizeHttpRequests(authorize -> authorize
        			.requestMatchers("/api/auth/**").permitAll()
        			.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        			.anyRequest().authenticated()
        			)
        	.exceptionHandling(exception -> exception
        			.authenticationEntryPoint(authenticationEntryPoint)
        	)
        	.sessionManagement(session -> session
        			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	//CORS
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		
		source.registerCorsConfiguration("/**", config);
		
		return new CorsFilter(source);
	}
}
