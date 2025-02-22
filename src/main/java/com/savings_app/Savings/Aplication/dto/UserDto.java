package com.savings_app.Savings.Aplication.dto;

import com.savings_app.Savings.Aplication.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private FinancialDataDto financialData;
    private List<ExpenseDto> expenses;
    private Role role;

}
