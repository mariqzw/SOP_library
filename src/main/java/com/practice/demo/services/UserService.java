package com.practice.demo.services;

import com.practice.demo.services.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void register(UserDto userDto);
    UserDto findByUsername(String username);
    UserDto findUserById(UUID id);
    List<UserDto> getAll();
    UserDto findUserDtoById(UUID uuid);
}
