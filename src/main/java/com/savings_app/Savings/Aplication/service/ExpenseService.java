package com.savings_app.Savings.Aplication.service;

import com.savings_app.Savings.Aplication.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> getAllExpensesByUserId(Long userId);

    ExpenseDto createExpense(Long userId, ExpenseDto expenseDto);

    ExpenseDto updateExpense(Long userId, Long expenseId, ExpenseDto expenseDto);

    void deleteExpense(Long userId, Long expenseId);

    ExpenseDto getExpenseById(Long userId, Long expenseId);

}
