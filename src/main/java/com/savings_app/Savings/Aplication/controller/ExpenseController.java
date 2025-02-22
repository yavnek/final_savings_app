package com.savings_app.Savings.Aplication.controller;

import com.savings_app.Savings.Aplication.dto.ExpenseDto;
import com.savings_app.Savings.Aplication.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users/{userId}/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    // Add Expense
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(
            @PathVariable("userId") Long userId,
            @RequestBody ExpenseDto expenseDto) {

        ExpenseDto savedExpense = expenseService.createExpense(userId, expenseDto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }
    // Get Expense
    @GetMapping("{expenseId}")
    public ResponseEntity<ExpenseDto> getExpenseById(
            @PathVariable("userId") Long userId,
            @PathVariable("expenseId") Long expenseId) {

        ExpenseDto expense = expenseService.getExpenseById(userId, expenseId);
        return ResponseEntity.ok(expense);
    }
    // Get All Expenses
    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(@PathVariable("userId") Long userId) {
        List<ExpenseDto> expenses = expenseService.getAllExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }
    // Update an Expense
    @PutMapping("{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(
            @PathVariable("userId") Long userId,
            @PathVariable("expenseId") Long expenseId,
            @RequestBody ExpenseDto expenseDto) {

        ExpenseDto updatedExpense = expenseService.updateExpense(userId, expenseId, expenseDto);
        return ResponseEntity.ok(updatedExpense);
    }
    // Delete an Expense
    @DeleteMapping("{expenseId}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable("userId") Long userId,
            @PathVariable("expenseId") Long expenseId) {

        expenseService.deleteExpense(userId, expenseId);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
