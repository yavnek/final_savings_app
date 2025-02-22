package com.savings_app.Savings.Aplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinancialDataDto {
    private Long id;
    private Long userId;
    private Double accountBalance;
    private Double monthlyIncome;
    private Double monthlyExpenses;
    private String otherFinancialInfo;
}
