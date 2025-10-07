package com.stanislav.financetracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stanislav.financetracker.entity.Transaction;
import com.stanislav.financetracker.repository.TransactionRepository;
import com.stanislav.financetracker.service.TransactionService;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class TransactionServiceImpl
 * @since 2025/10/07 - 11.09
 */

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository repository;
	
	public TransactionServiceImpl(TransactionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public boolean existsByNameAndUserId(String name, Long userId) {
		return repository.existsByNameAndUserId(name, userId);
	}
	
	@Override
	public boolean existsByIdAndUserId(Long id, Long userId) {
		return repository.existsByIdAndUserId(id, userId);
	}
	
	@Override
	public List<Transaction> getAllByUserId(Long userId){
		return repository.findAllByUserId(userId);
	}
	
	@Override
	public Transaction create(Transaction transaction) {
		if (transaction.getUser() == null || transaction.getUser().getId() == null) {
			throw new IllegalArgumentException("User must be specified for the transaction");
		}
		
		if(repository.existsByNameAndUserId(transaction.getName(), transaction.getUser().getId())) {
			throw new IllegalArgumentException("Transaction with name '" + transaction.getName() + "' is already exists");
		}
		
		return repository.save(transaction);
	}
	
	@Override
	public Transaction update(Transaction transaction) {
		if (transaction.getUser() == null || transaction.getUser().getId() == null) {
			throw new IllegalArgumentException("User must be specified for the transaction");
		}
		
		Long idOfDuplicate = repository.findIdByNameAndUserId(transaction.getName(), transaction.getUser().getId());
		
		if (idOfDuplicate != null && !idOfDuplicate.equals(transaction.getId())) {
			throw new IllegalArgumentException("Transaction with name '" + transaction.getName() + "' is already exists");
		}
		
		return repository.save(transaction);
	}
	
	@Override
	public void deleteByIdAndUserId(Long id, Long userId) {
		Transaction transaction = repository.findByIdAndUserId(id, userId);
		
		if (transaction == null) {
			throw new IllegalArgumentException("Transaction not found for this user");
		}
		
		repository.delete(transaction);
	}
	
	@Override
	public Transaction getByIdAndUserId(Long id, Long userId) {
		return repository.findByIdAndUserId(id, userId);
	}
}