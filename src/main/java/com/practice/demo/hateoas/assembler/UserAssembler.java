package com.practice.demo.hateoas.assembler;

import com.practice.demo.controllers.ReservationController;
import com.practice.demo.controllers.UserController;
import com.practice.demo.hateoas.model.UserModel;
import com.practice.demo.services.dtos.UserDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    @NonNull
    public UserModel toModel(@NonNull UserDto entity) {
        UserModel model = createModelWithId(entity.getId(), entity);
        model.add(linkTo(methodOn(UserController.class)
                .getAllUsers())
                .withRel("users"));

        model.add(linkTo(methodOn(ReservationController.class)
                .getReservationsByUser(entity.getId()))
                .withRel("getAllByUser")
                .withType("GET")
                .andAffordance(afford(methodOn(ReservationController.class)
                        .getReservationsByUser(entity.getId()))));

        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());

        return model;
    }
}
