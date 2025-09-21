package com.stanislav.financetracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stanislav.financetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserNameOrEmail(String name, String email);

}
