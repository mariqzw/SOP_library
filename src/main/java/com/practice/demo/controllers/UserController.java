package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.UserAssembler;
import com.practice.demo.hateoas.model.UserModel;
import com.practice.demo.services.UserService;
import com.practice.demo.services.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
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

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

    @GetMapping("/all")
    public CollectionModel<UserModel> getAllUsers() {
        return userAssembler.toCollectionModel(userService.getAll());
    }

    @GetMapping("/id/{id}")
    public UserModel getUserById(@PathVariable UUID id) {
        return userAssembler.toModel(userService.findUserDtoById(id));
    }

    @GetMapping("/username/{username}")
    public UserModel getUserByUsername(@PathVariable String username) {
        return userAssembler.toModel(userService.findByUsername(username));
    }
}
