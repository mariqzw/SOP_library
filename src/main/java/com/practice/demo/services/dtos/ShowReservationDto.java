package com.practice.demo.services.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShowReservationDto extends BaseEntityDto {
    private UUID bookId;
    private UUID userId;
//    private BookDto book;
//    private UserDto user;
    private LocalDateTime reservedAt;
    private LocalDateTime reservedUntil;

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

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
