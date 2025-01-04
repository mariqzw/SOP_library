package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.ReservationAssembler;
import com.practice.demo.services.ReservationService;
import org.libraryapi.controllers.ReservationApi;
import org.libraryapi.dto.ReservationDto;
import org.libraryapi.model.ReservationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController implements ReservationApi {
    private ReservationService reservationService;
    private ReservationAssembler reservationAssembler;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Autowired
    public void setReservationAssembler(ReservationAssembler reservationAssembler) {
        this.reservationAssembler = reservationAssembler;
    }

    @Override
    @PostMapping("/add")
    public void addReservation(@RequestBody ReservationDto reservationDto, String username) {
        reservationService.register(reservationDto, username);
    }

    @Override
    @GetMapping("/user/id/{id}")
    public CollectionModel<ReservationModel> getReservationsByUser(@PathVariable UUID id) {
        return reservationAssembler.toCollectionModel(reservationService.findAllReservationsByUser(id));
    }

    @Override
    @GetMapping("/user/username/{username}")
    public CollectionModel<ReservationModel> getReservationsByUsername(@PathVariable String username) {
        return reservationAssembler.toCollectionModel(reservationService.findAllReservationsByUsername(username));
    }

    @Override
    @GetMapping("/all")
    public CollectionModel<ReservationModel> getAllReservations() {
        return reservationAssembler.toCollectionModel(reservationService.getAll());
    }
}
