package com.stanislav.financetracker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stanislav.financetracker.entity.Category;
import com.stanislav.financetracker.repository.CategoryRepository;
import com.stanislav.financetracker.service.CategoryService;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategoryServiceImpl
 * @since 2025/09/28 - 16.03
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;
	
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Category> getByUserId(Long userId){
		return repository.findByUserId(userId);
	}
	
	@Override
	public Optional<Category> getByIdAndUserId(Long id, Long userId){
		return repository.findByIdAndUserId(id, userId);
	}
	
	@Override
	public Category create(Category category) {
		if (category.getUser() == null || category.getUser().getId() == null) {
			throw new IllegalArgumentException("User must be spcified for the category");
		}
		else if (repository.existsByNameAndUserId(category.getName(), category.getUser().getId())) {
			throw new IllegalArgumentException("Category with name '" + category.getName() + "' is already exists");
		}
		
		return repository.save(category);
	}
	
	@Override
	public Category update(Category category) {
		if (category.getUser() == null || category.getUser().getId() == null) {
			throw new IllegalArgumentException("User must be spcified for the category");
		}
		
		Long idOfDuplicate = repository.findIdByNameAndUserId(category.getName(), category.getUser().getId());
		
		if (idOfDuplicate != null && !idOfDuplicate.equals(category.getId())) {
			throw new IllegalArgumentException("Category with name '" + category.getName() + "' is already exists");
		}
		
		return repository.save(category);
	}
	
	@Override
	public boolean existsByNameAndUserId(String name, Long userId) {
		return repository.existsByNameAndUserId(name, userId);
	}
	
	@Override
	public void deleteByIdAndUserId(Long id, Long userId) {
		Optional<Category> category = repository.findByIdAndUserId(id, userId);
		if (category.isEmpty()) {
			throw new IllegalArgumentException("Category not found for this user");
		}
		
		repository.delete(category.get());
	}
}
