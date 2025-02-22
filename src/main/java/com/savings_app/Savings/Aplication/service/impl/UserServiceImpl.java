package com.savings_app.Savings.Aplication.service.impl;


import com.savings_app.Savings.Aplication.dto.UserDto;
import com.savings_app.Savings.Aplication.entity.User;
import com.savings_app.Savings.Aplication.exception.ResourceNotFoundException;
import com.savings_app.Savings.Aplication.mapper.ExpenseMapper;
import com.savings_app.Savings.Aplication.mapper.FinancialDataMapper;
import com.savings_app.Savings.Aplication.mapper.UserMapper;
import com.savings_app.Savings.Aplication.repository.UserRepository;
import com.savings_app.Savings.Aplication.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User does not exist with the given Id: " + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public User getUserEntityById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist with the given Id")
        );

        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());

        if (updatedUser.getFinancialData() != null) {
            if (user.getFinancialData() == null) {

                user.setFinancialData(
                        FinancialDataMapper.mapToFinancialData(updatedUser.getFinancialData(), user)
                );
            } else {

                user.getFinancialData().setAccountBalance(updatedUser.getFinancialData().getAccountBalance());
                user.getFinancialData().setMonthlyIncome(updatedUser.getFinancialData().getMonthlyIncome());
                user.getFinancialData().setMonthlyExpenses(updatedUser.getFinancialData().getMonthlyExpenses());
                user.getFinancialData().setOtherFinancialInfo(updatedUser.getFinancialData().getOtherFinancialInfo());
            }
        }


        if (updatedUser.getExpenses() != null) {

            user.getExpenses().clear();

            user.getExpenses().addAll(
                    updatedUser.getExpenses().stream()
                            .map(expenseDto -> ExpenseMapper.mapToExpense(expenseDto, user))
                            .collect(Collectors.toList())
            );
        }

        User updatedUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist with the given Id" + userId)
        );

        userRepository.deleteById(userId);

    }
}
