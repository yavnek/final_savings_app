package com.savings_app.Savings.Aplication.mapper;

import com.savings_app.Savings.Aplication.dto.ExpenseDto;
import com.savings_app.Savings.Aplication.entity.Expense;
import com.savings_app.Savings.Aplication.entity.User;


public class ExpenseMapper {
    //Obj Expense -> Data
    public static ExpenseDto mapToExpenseDto(Expense expense) {
        if (expense != null) {
            return new ExpenseDto(
                    expense.getId(),
                    expense.getUser() != null ? expense.getUser().getId() : null,
                    expense.getCategory(),
                    expense.getAmount(),
                    expense.getDate(),
                    expense.getDescription()
            );
        } else {
            return null;
        }
    }
    //Data -> Obj Expense
    public static Expense mapToExpense(ExpenseDto expenseDto, User user) {
        if (expenseDto != null) {
            return new Expense(
                    expenseDto.getId(),
                    user,
                    expenseDto.getCategory(),
                    expenseDto.getAmount(),
                    expenseDto.getDate(),
                    expenseDto.getDescription()
            );
        } else {
            return null;
        }
    }
}
