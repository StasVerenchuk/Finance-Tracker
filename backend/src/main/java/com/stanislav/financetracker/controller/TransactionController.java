package com.stanislav.financetracker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanislav.financetracker.dto.transaction.TransactionResponse;
import com.stanislav.financetracker.dto.transaction.TransactionSaveRequest;
import com.stanislav.financetracker.entity.Transaction;
import com.stanislav.financetracker.mapper.TransactionMapper;
import com.stanislav.financetracker.service.TransactionService;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class TransactionController
 * @since 2025/10/07 - 11.09
 */

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

	private final TransactionService transactionService;
	private final TransactionMapper mapper;
	
	public TransactionController(TransactionService transactionService, TransactionMapper mapper) {
		this.transactionService = transactionService;
		this.mapper = mapper;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam Long userId) {
		List<Transaction> transactions = transactionService.getAllByUserId(userId);
		
		List<TransactionResponse> responseList = transactions.stream()
				.map(mapper::toResponse)
				.toList();
		
		return ResponseEntity.ok(responseList);
	}
	
	@PostMapping("/add")
	public ResponseEntity<TransactionResponse> create(@RequestBody TransactionSaveRequest request) {
		Transaction transaction = new Transaction();
		mapper.updateEntity(request, transaction);
		transaction = transactionService.create(transaction);
		
		TransactionResponse response = mapper.toResponse(transaction);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TransactionResponse> update(@PathVariable Long id, @RequestBody TransactionSaveRequest request, @RequestParam Long userId) {
		Transaction existingTransaction = transactionService.getByIdAndUserId(id, userId);
		
		if (existingTransaction == null) {
			return ResponseEntity.notFound().build();
		}
		
		mapper.updateEntity(request, existingTransaction);
		
		Transaction updateTransaction = transactionService.update(existingTransaction);
		
		TransactionResponse response = mapper.toResponse(updateTransaction);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id, @RequestParam Long userId) {
		try {
			transactionService.deleteByIdAndUserId(id, userId);
			
			return ResponseEntity.noContent().build();
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
