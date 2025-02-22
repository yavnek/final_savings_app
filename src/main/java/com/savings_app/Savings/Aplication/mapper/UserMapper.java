package com.savings_app.Savings.Aplication.mapper;

import com.savings_app.Savings.Aplication.dto.UserDto;
import com.savings_app.Savings.Aplication.entity.FinancialData;
import com.savings_app.Savings.Aplication.entity.Role;
import com.savings_app.Savings.Aplication.entity.User;
import java.util.Collections;
import java.util.stream.Collectors;

public class UserMapper {

    //User -> Data
    public static UserDto mapToUserDto(User user) {
        if (user != null) {
            UserDto userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setRole(user.getRole());
            userDTO.setFinancialData(FinancialDataMapper.mapToFinancialDataDto(user.getFinancialData())); // Assuming a mapper exists for FinancialData

            userDTO.setExpenses(user.getExpenses() != null ?
                    user.getExpenses().stream()
                            .map(ExpenseMapper::mapToExpenseDto)
                            .collect(Collectors.toList()) : null);
            return userDTO;
        }

        return null;
    }
    //Data -> Obj User
    public static User mapToUser(UserDto userDto) {

        User user = new User();

        FinancialData financialData;

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        if(userDto.getRole()== Role.ADMIN || userDto.getRole()== Role.USER){
            user.setRole(userDto.getRole());
        }else {
            user.setRole(Role.USER);
        }

        if (userDto.getFinancialData() != null) {
            financialData = FinancialDataMapper.mapToFinancialData(userDto.getFinancialData(), user);
        } else {
            financialData = new FinancialData();
            financialData.setAccountBalance(0.0);
            financialData.setMonthlyIncome(0.0);
            financialData.setMonthlyExpenses(0.0);
            financialData.setUser(user);
            financialData.setOtherFinancialInfo("");
        }
        user.setFinancialData(financialData);

        if (userDto.getExpenses() != null) {
            user.setExpenses(
                    userDto.getExpenses().stream()
                            .map(expenseDto -> ExpenseMapper.mapToExpense(expenseDto, user))
                            .collect(Collectors.toList())
            );
        } else {
            user.setExpenses(Collections.emptyList());
        }
        return user;

    }

}
