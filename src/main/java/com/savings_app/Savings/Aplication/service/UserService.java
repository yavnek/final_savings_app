package com.savings_app.Savings.Aplication.service;

import com.savings_app.Savings.Aplication.dto.UserDto;
import com.savings_app.Savings.Aplication.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    User getUserEntityById(Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto updatedUser);

    void deleteUser(Long userId);

}
