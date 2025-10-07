package com.stanislav.financetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stanislav.financetracker.entity.Transaction;

/*
 * @author Stanislav Verenchuk
 * @version 1.0.0
 * @project Finance-Tracker
 * @class TransactionRepository
 * @since 202510/07 - 11.04
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findAllByUserId(Long userId);
	
	boolean existsByNameAndUserId(String name, Long userId);
	
	boolean existsByIdAndUserId(Long id, Long userId);
	
	@Query("SELECT c.id FROM Transaction c WHERE c.name = :name AND c.user.id = :userId")
	Long findIdByNameAndUserId(@Param("name") String name, @Param("userId") Long userId);
	
	Transaction findByIdAndUserId(Long id, Long userId);
}
