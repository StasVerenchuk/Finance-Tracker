package com.stanislav.financetracker.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${app.jwt-secret}")
	private String jwtSecretKey;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationDate;
	
	public String generateJwtToken(Authentication auth) {
		
		String email = auth.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);
		
		String jwtToken = Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(expirationDate)
				.signWith(key())
				.compact();
		
		return jwtToken;
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
	}
	
	public String getUsernameFromToken(String jwtToken) {
		return Jwts.parser()
				.verifyWith((SecretKey) key())
				.build()
				.parseSignedClaims(jwtToken)
				.getPayload()
				.getSubject();
	}
	
	public boolean validateToken(String jwtToken) {
		Jwts.parser()
			.verifyWith((SecretKey) key())
			.build()
			.parse(jwtToken);
		
		return true;
	}
}
