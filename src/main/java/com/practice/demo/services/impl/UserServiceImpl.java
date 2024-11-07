package com.practice.demo.services.impl;

import com.practice.demo.models.User;
import com.practice.demo.repositories.UserRepository;
import com.practice.demo.services.UserService;
import com.practice.demo.services.dtos.BookDto;
import com.practice.demo.services.dtos.UserDto;
import com.practice.demo.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void register(UserDto userDto) {
        if (!this.validationUtil.isValid(userDto)) {

            this.validationUtil
                    .violations(userDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }
        userRepository.findByUsername(userDto.getUsername()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        });
        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) {
            userRepository.saveAndFlush(modelMapper.map(userDto, User.class));
        }

    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map((u) -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(u -> modelMapper.map(u, UserDto.class))
                .orElse(null);
    }

    @Override
    public UserDto findUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElse(null);
    }

    @Override
    public UserDto findUserDtoById(UUID uuid) {
        return modelMapper.map(this.userRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("No user with this ID")), UserDto.class);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
