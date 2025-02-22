package com.savings_app.Savings.Aplication.service;

import com.savings_app.Savings.Aplication.dto.FinancialDataDto;

public interface FinancialDataService {

    FinancialDataDto getFinancialDataByUserId(Long userId);

    FinancialDataDto saveFinancialData(Long userId, FinancialDataDto financialDataDto);

    FinancialDataDto updateFinancialData(Long userId, FinancialDataDto financialDataDto);

    void deleteFinancialData(Long userId);
}
