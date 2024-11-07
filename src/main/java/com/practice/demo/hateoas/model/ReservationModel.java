package com.practice.demo.hateoas.model;

import com.practice.demo.services.dtos.BookDto;
import com.practice.demo.services.dtos.UserDto;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class ReservationModel extends RepresentationModel<ReservationModel> {
//    private BookDto book;
//    private UserDto user;
    private LocalDateTime reservedAt;
    private LocalDateTime reservedUntil;

    protected ReservationModel() {};

    public ReservationModel(LocalDateTime reservedAt, LocalDateTime reservedUntil) {
        this.reservedAt = reservedAt;
        this.reservedUntil = reservedUntil;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

}
