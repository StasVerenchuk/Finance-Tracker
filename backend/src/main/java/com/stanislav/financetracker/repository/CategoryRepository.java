package com.stanislav.financetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stanislav.financetracker.entity.Category;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class CategoryMapper
 * @since 2025/09/28 - 14.30
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByNameAndUserId(String name, Long userId);
	
	
	@Query("SELECT c.id FROM Category c WHERE c.name = :name AND c.user.id = :userId")
	Long findIdByNameAndUserId(@Param("name") String name, @Param("userId") Long userId);
	
	/**
	 * Get all categories that belong to the specified user.
	 * <p>
	 * This operation returns list of categories associated with the given
	 * {@code userId}. If the user has no categories, empty list is returned.
	 *
	 * @param userId the ID of the user whose categories should be returned
	 */
	List<Category> findByUserId(Long userId);
	
	Optional<Category> findByIdAndUserId(Long id, Long userId);
	
	void deleteByIdAndUserId(Long id, Long userId);
}
