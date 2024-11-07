package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.UserAssembler;
import com.practice.demo.hateoas.model.UserModel;
import com.practice.demo.models.Book;
import com.practice.demo.models.User;
import com.practice.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/all")
    public CollectionModel<UserModel> getAllUsers() {
        return userAssembler.toCollectionModel(userService.getAll());
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable UUID id) {
        return userAssembler.toModel(userService.findUserDtoById(id));
    }
}
