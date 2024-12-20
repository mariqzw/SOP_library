package com.practice.demo.services.dtos;

import com.practice.demo.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

public class ShowReservationDto extends BaseEntityDto {
    private UUID bookId;
    private UUID userId;
//    private BookDto book;
//    private UserDto user;
    private LocalDate reservedAt;
    private LocalDate reservedUntil;
    private Status status;

    public LocalDate getReservedAt() {
        return reservedAt;
    }

    public LocalDate getReservedUntil() {
        return reservedUntil;
    }

    public UUID getBookId() {
        return bookId;
    }

    public UUID getUserId() {
        return userId;
    }
}
