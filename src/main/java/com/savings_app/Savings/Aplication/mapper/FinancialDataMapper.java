package com.savings_app.Savings.Aplication.mapper;

import com.savings_app.Savings.Aplication.dto.FinancialDataDto;
import com.savings_app.Savings.Aplication.entity.FinancialData;
import com.savings_app.Savings.Aplication.entity.User;

public class FinancialDataMapper {

    //Obj FinancialData -> Data
    public static FinancialDataDto mapToFinancialDataDto(FinancialData financialData) {
        if (financialData != null) {
            return new FinancialDataDto(
                    financialData.getId(),
                    financialData.getUser().getId(),
                    financialData.getAccountBalance(),
                    financialData.getMonthlyIncome(),
                    financialData.getMonthlyExpenses(),
                    financialData.getOtherFinancialInfo()
            );
        } else {

            return null;
        }
    }

    //Data -> Obj FinancialData
    public static FinancialData mapToFinancialData(FinancialDataDto financialDataDto, User user) {
            if(financialDataDto != null) {
                return new FinancialData(
                        financialDataDto.getId(),
                        user,
                        financialDataDto.getAccountBalance(),
                        financialDataDto.getMonthlyIncome(),
                        financialDataDto.getMonthlyExpenses(),
                        financialDataDto.getOtherFinancialInfo()
                );
            }
            else {
                return null;
            }
    }
}
