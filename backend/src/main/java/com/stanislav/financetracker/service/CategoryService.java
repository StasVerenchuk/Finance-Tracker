package com.stanislav.financetracker.service;

import java.util.List;
import java.util.Optional;

import com.stanislav.financetracker.entity.Category;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategoryService
 * @since 2025/09/28 - 15.58
 */

public interface CategoryService {
	
	boolean existsByNameAndUserId(String name, Long userId);
	
	Category create (Category category);
	
	Category update (Category category);
	
	Optional<Category> getByIdAndUserId(Long id, Long userId);
	
	/**
	 * Get all categories that belong to the specified user.
	 * <p>
	 * This operation returns list of categories associated with the given
	 * {@code userId}. If the user has no categories, empty list is returned.
	 *
	 * @param userId the ID of the user whose categories should be returned
	 */
	List<Category> getByUserId(Long userId);
	
	/**
	 * Deletes category that belong to the specified user.
	 * <p>
	 * This operation removes category associated with the given
	 * {@code userId}. If the user has no categories, no action is performed.
	 *
	 * @param userId the ID of the user whose category should be deleted
	 */
	void deleteByIdAndUserId(Long id, Long userId);
}
