package com.practice.demo.hateoas.assembler;

import com.practice.demo.controllers.BookController;
import com.practice.demo.controllers.ReservationController;
import com.practice.demo.controllers.UserController;
import com.practice.demo.hateoas.model.ReservationModel;
import com.practice.demo.services.dtos.ReservationDto;
import com.practice.demo.services.dtos.ShowReservationDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReservationAssembler extends RepresentationModelAssemblerSupport<ShowReservationDto, ReservationModel> {
    public ReservationAssembler() {
        super(ReservationController.class, ReservationModel.class);
    }

    @Override
    @NonNull
    public ReservationModel toModel(@NonNull ShowReservationDto entity) {
        ReservationModel model = createModelWithId(entity.getId(), entity);
        model.add(linkTo(methodOn(ReservationController.class)
                .getAllReservations())
                .withRel("reservations"));
        model.add(linkTo(methodOn(BookController.class)
                .findBookById(entity.getBookId()))
                .withRel("book"));
        model.add(linkTo(methodOn(UserController.class)
                .getUserById(entity.getUserId()))
                .withRel("user"));

        model.add(linkTo(methodOn(ReservationController.class)
                .getReservationsByUser(entity.getUserId()))
                .withRel("getAllByUser")
                .withType("GET")
                .andAffordance(afford(methodOn(ReservationController.class)
                        .getReservationsByUser(entity.getUserId()))));

        model.setReservedAt(entity.getReservedAt());
        model.setReservedUntil(entity.getReservedUntil());


        return model;
    }
}
