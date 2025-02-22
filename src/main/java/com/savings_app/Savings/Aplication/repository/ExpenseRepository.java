package com.savings_app.Savings.Aplication.repository;

import com.savings_app.Savings.Aplication.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
   List<Expense> findByUserId(Long userId);
}
