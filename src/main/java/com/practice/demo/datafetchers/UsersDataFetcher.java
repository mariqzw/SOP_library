package com.practice.demo.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.practice.demo.services.UserService;
import org.libraryapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class UsersDataFetcher {

    private final UserService userService;

    @Autowired
    public UsersDataFetcher(UserService userService) {
        this.userService = userService;
    }

    @DgsQuery
    public List<UserDto> users(@InputArgument String usernameFilter) {
        List<UserDto> users = userService.getAll();

        if (usernameFilter == null) {
            return users;
        }

        return users.stream()
                .filter(book -> book.getUsername().contains(usernameFilter))
                .collect(Collectors.toList());
    }

    @DgsMutation
    public UserDto addUser(@InputArgument(name = "user") UserDto submittedUser) {
        UserDto userDto = new UserDto();
        userDto.setUsername(submittedUser.getUsername());
        userDto.setEmail(submittedUser.getEmail());
        userDto.setName(submittedUser.getName());
        userDto.setSurname(submittedUser.getSurname());

        userService.register(userDto);

        return userDto;
    }
}


