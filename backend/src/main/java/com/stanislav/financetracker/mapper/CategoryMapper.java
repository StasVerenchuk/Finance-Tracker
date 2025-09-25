package com.stanislav.financetracker.mapper;

import org.springframework.stereotype.Component;

import com.stanislav.financetracker.dto.category.CategoryResponse;
import com.stanislav.financetracker.dto.category.CategorySaveRequest;
import com.stanislav.financetracker.entity.Category;
import com.stanislav.financetracker.entity.User;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategoryResponse
 * @since 2025/09/25 - 12.56
 */

@Component
public class CategoryMapper {

	public CategoryResponse toResponse(Category entity) {
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
		
		return new CategoryResponse(entity.getId(), entity.getName(), entity.getColor(), entity.getType(), userId);
	}
	
	public void updateEntity(CategorySaveRequest request, Category entity) {
		entity.setName(request.getName());
		entity.setColor(request.getColorHex());
		entity.setType(request.getType());
		
		User user = new User();
		user.setId(request.getUserId());
		entity.setUser(user);
	}
}
