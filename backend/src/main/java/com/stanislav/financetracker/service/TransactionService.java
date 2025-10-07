package com.stanislav.financetracker.service;

import java.util.List;

import com.stanislav.financetracker.entity.Transaction;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class TransactionService
 * @since 2025/10/07 - 11.09
 */

public interface TransactionService {

	boolean existsByNameAndUserId(String name, Long userId);
	
	boolean existsByIdAndUserId(Long id, Long userId);
	
	List<Transaction> getAllByUserId(Long userId);
	
	Transaction create (Transaction transaction);
	
	Transaction update (Transaction transaction);
	
	void deleteByIdAndUserId(Long id, Long userId);
	
	Transaction getByIdAndUserId(Long id, Long userId);
}
