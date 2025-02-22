package com.savings_app.Savings.Aplication.service.impl;

import com.savings_app.Savings.Aplication.dto.ExpenseDto;
import com.savings_app.Savings.Aplication.entity.Expense;
import com.savings_app.Savings.Aplication.entity.User;
import com.savings_app.Savings.Aplication.exception.ResourceNotFoundException;
import com.savings_app.Savings.Aplication.mapper.ExpenseMapper;
import com.savings_app.Savings.Aplication.repository.ExpenseRepository;
import com.savings_app.Savings.Aplication.repository.UserRepository;
import com.savings_app.Savings.Aplication.service.ExpenseService;
import com.savings_app.Savings.Aplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<ExpenseDto> getAllExpensesByUserId(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(ExpenseMapper::mapToExpenseDto)
                .toList();
    }

    @Override
    public ExpenseDto createExpense(Long userId, ExpenseDto expenseDto) {
        User user = userService.getUserEntityById(userId);
        Expense expense = ExpenseMapper.mapToExpense(expenseDto, user);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto updateExpense(Long userId, Long expenseId, ExpenseDto expenseDto) {
        User user = userService.getUserEntityById(userId);
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        if (!expense.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Expense does not belong to the user with id: " + userId);
        }
        expense.setCategory(expenseDto.getCategory());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        expense.setDescription(expenseDto.getDescription());

        Expense updatedExpense = expenseRepository.save(expense);

        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long userId, Long expenseId) {

        User user = userService.getUserEntityById(userId);

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        if (!expense.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Expense does not belong to the user with id: " + userId);
        }

        expenseRepository.delete(expense);
    }

    @Override
    public ExpenseDto getExpenseById(Long userId, Long expenseId) {
        User user = userService.getUserEntityById(userId);

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        if (!expense.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Expense does not belong to the user with id: " + userId);
        }

        return ExpenseMapper.mapToExpenseDto(expense);
    }

}
