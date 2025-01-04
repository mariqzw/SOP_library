package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.UserAssembler;
import com.practice.demo.services.UserService;
import org.libraryapi.controllers.UserApi;
import org.libraryapi.dto.UserDto;
import org.libraryapi.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {
    private UserService userService;
    private UserAssembler userAssembler;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserAssembler(UserAssembler userAssembler) {
        this.userAssembler = userAssembler;
    }

    @Override
    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

    @Override
    @GetMapping("/all")
    public CollectionModel<UserModel> getAllUsers() {
        return userAssembler.toCollectionModel(userService.getAll());
    }

    @Override
    @GetMapping("/id/{id}")
    public UserModel getUserById(@PathVariable UUID id) {
        return userAssembler.toModel(userService.findUserDtoById(id));
    }

    @Override
    @GetMapping("/username/{username}")
    public UserModel getUserByUsername(@PathVariable String username) {
        return userAssembler.toModel(userService.findByUsername(username));
    }
}
