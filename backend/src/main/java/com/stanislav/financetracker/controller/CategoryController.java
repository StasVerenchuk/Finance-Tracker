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

import com.stanislav.financetracker.dto.category.CategoryResponse;
import com.stanislav.financetracker.dto.category.CategorySaveRequest;
import com.stanislav.financetracker.entity.Category;
import com.stanislav.financetracker.mapper.CategoryMapper;
import com.stanislav.financetracker.service.CategoryService;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategoryController
 * @since 2025/09/29 - 13.20
 */

@RestController
@RequestMapping("api/category")
public class CategoryController {

	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;
	
	public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
		this.categoryService = categoryService;
		this.categoryMapper = categoryMapper;
	}
	
	@GetMapping("/my-categories")
	public ResponseEntity<List<CategoryResponse>> getCategories(@RequestParam Long userId){
		List<Category> categories = categoryService.getByUserId(userId);
		
		List<CategoryResponse> responseList = categories.stream()
				.map(categoryMapper::toResponse)
				.toList();
		
		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping("/my-category/{id}")
	public ResponseEntity<CategoryResponse> getById(@PathVariable Long id, @RequestParam Long userId){
		return categoryService.getByIdAndUserId(id, userId)
				.map(categoryMapper::toResponse)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/add")
	public ResponseEntity<CategoryResponse> create(@RequestBody CategorySaveRequest request){
		Category category = new Category();
		categoryMapper.updateEntity(request, category);
		category = categoryService.create(category);
		
		CategoryResponse response = categoryMapper.toResponse(category);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategorySaveRequest request, @RequestParam Long userId){
		Optional<Category> categoryOpt = categoryService.getByIdAndUserId(id, userId);
		if (categoryOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Category category = categoryOpt.get();
		categoryMapper.updateEntity(request, category);
		category = categoryService.update(category);
		
		CategoryResponse response = categoryMapper.toResponse(category);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id, @RequestParam Long userId){
		try {
			categoryService.deleteByIdAndUserId(id, userId);
			
			return ResponseEntity.noContent().build();
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}





















