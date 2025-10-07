package com.stanislav.financetracker.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.stanislav.financetracker.dto.transaction.TransactionResponse;
import com.stanislav.financetracker.dto.transaction.TransactionSaveRequest;
import com.stanislav.financetracker.entity.Category;
import com.stanislav.financetracker.entity.CategoryType;
import com.stanislav.financetracker.entity.Transaction;
import com.stanislav.financetracker.entity.User;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class TransactionMapper
 * @since 2025/09/30 - 14.26
*/

@Component
public class TransactionMapper {

	public TransactionResponse toResponse(Transaction entity) {
		if(entity == null) {
			return null;
		}
		
		Long userId;
		if(entity.getUser() != null) {
			userId = entity.getUser().getId();
		}
		else {
			userId = null;
		}
		
		Long categoryId;
		if(entity.getCategory() != null) {
			categoryId = entity.getCategory().getId();
		}
		else {
			categoryId = null;
		}
		
		return new TransactionResponse(entity.getId(), entity.getName(), entity.getAmount(),
				entity.getDescription(), entity.getDate(), entity.getType(), userId, categoryId);
	}
	
	public void updateEntity(TransactionSaveRequest request, Transaction entity) {
		entity.setName(request.getName());
		entity.setAmount(request.getAmount());
		entity.setDescription(request.getDescription());
		entity.setDate(request.getDate());
		entity.setType(request.getType());
		
		User user = new User();
		user.setId(request.getUserId());
		entity.setUser(user);
		
		Category category = new Category();
		category.setId(request.getCategoryId());
		entity.setCategory(category);
	}
}
