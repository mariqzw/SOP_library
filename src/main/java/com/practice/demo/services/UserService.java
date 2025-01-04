package com.practice.demo.services;

import java.util.List;
import java.util.UUID;
import org.libraryapi.dto.UserDto;

public interface UserService {
    void register(UserDto userDto);
    UserDto findByUsername(String username);
    UserDto findUserById(UUID id);
    List<UserDto> getAll();
    UserDto findUserDtoById(UUID uuid);
}
