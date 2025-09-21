package com.stanislav.financetracker.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stanislav.financetracker.entity.User;
import com.stanislav.financetracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by " + usernameOrEmail + " Username or Email"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())   // краще брати email із БД, а не те що ввів користувач
                .password(user.getPasswordHash())
                .authorities("USER")
                .build();

    }
}
