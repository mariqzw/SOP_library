package com.practice.demo.hateoas.model;

import com.practice.demo.enums.Status;
import com.practice.demo.services.dtos.BookDto;
import com.practice.demo.services.dtos.UserDto;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class ReservationModel extends RepresentationModel<ReservationModel> {
    private LocalDate reservedAt;
    private LocalDate reservedUntil;
    private Status status;

    protected ReservationModel() {};

    public ReservationModel(LocalDate reservedAt, LocalDate reservedUntil, Status status) {
        this.reservedAt = reservedAt;
        this.reservedUntil = reservedUntil;
        this.status = status;
    }

    public LocalDate getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDate reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDate getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDate reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
