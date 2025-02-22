package com.savings_app.Savings.Aplication.service.impl;

import com.savings_app.Savings.Aplication.dto.FinancialDataDto;
import com.savings_app.Savings.Aplication.entity.FinancialData;
import com.savings_app.Savings.Aplication.entity.User;
import com.savings_app.Savings.Aplication.exception.ResourceNotFoundException;
import com.savings_app.Savings.Aplication.mapper.FinancialDataMapper;
import com.savings_app.Savings.Aplication.repository.FinancialDataRepository;
import com.savings_app.Savings.Aplication.repository.UserRepository;
import com.savings_app.Savings.Aplication.service.FinancialDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialDataServiceImpl  implements FinancialDataService {

    private final FinancialDataRepository financialDataRepository;
    private final UserRepository userRepository;

    @Autowired
    public FinancialDataServiceImpl(FinancialDataRepository financialDataRepository, UserRepository userRepository) {
        this.financialDataRepository = financialDataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialDataDto getFinancialDataByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        FinancialData financialData = user.getFinancialData();

        return financialData != null ? FinancialDataMapper.mapToFinancialDataDto(financialData) : null;
    }

    @Override
    public FinancialDataDto saveFinancialData(Long userId, FinancialDataDto financialDataDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        FinancialData financialData = FinancialDataMapper.mapToFinancialData(financialDataDto, user);
        user.setFinancialData(financialData);

        userRepository.save(user);

        return FinancialDataMapper.mapToFinancialDataDto(financialData);
    }

    @Override
    public FinancialDataDto updateFinancialData(Long userId, FinancialDataDto financialDataDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        FinancialData financialData = user.getFinancialData();
        if (financialData == null) {
            throw new ResourceNotFoundException("Financial data not found for user with ID: " + userId);
        }

        financialData.setAccountBalance(financialDataDto.getAccountBalance());
        financialData.setMonthlyIncome(financialDataDto.getMonthlyIncome());
        financialData.setMonthlyExpenses(financialDataDto.getMonthlyExpenses());
        financialData.setOtherFinancialInfo(financialDataDto.getOtherFinancialInfo());

        financialDataRepository.save(financialData);

        return FinancialDataMapper.mapToFinancialDataDto(financialData);
    }

    @Override
    public void deleteFinancialData(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        FinancialData financialData = user.getFinancialData();
        if (financialData != null) {
            financialDataRepository.delete(financialData);
            user.setFinancialData(null);
            userRepository.save(user);
        }
    }
}
