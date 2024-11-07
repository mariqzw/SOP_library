package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.ReservationAssembler;
import com.practice.demo.hateoas.model.ReservationModel;
import com.practice.demo.hateoas.model.UserModel;
import com.practice.demo.models.Reservation;
import com.practice.demo.services.ReservationService;
import com.practice.demo.services.dtos.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
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

    @PostMapping("/add")
    void addReservation(@RequestBody ReservationDto reservationDto, String username) {
        reservationService.register(reservationDto, username);
    }

    @GetMapping("/user/id/{id}")
    public CollectionModel<ReservationModel> getReservationsByUser(@PathVariable UUID id) {
        return reservationAssembler.toCollectionModel(reservationService.findAllReservationsByUser(id));
    }

    @GetMapping("/user/username/{username}")
    public CollectionModel<ReservationModel> getReservationsByUsername(@PathVariable String username) {
        return reservationAssembler.toCollectionModel(reservationService.findAllReservationsByUsername(username));
    }

    @GetMapping("/all")
    public CollectionModel<ReservationModel> getAllReservations() {
        return reservationAssembler.toCollectionModel(reservationService.getAll());
    }
}
