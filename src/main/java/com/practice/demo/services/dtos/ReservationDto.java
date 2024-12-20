package com.practice.demo.services.dtos;

import com.practice.demo.models.Book;

import java.time.LocalDate;

public class ReservationDto extends BaseEntityDto {
    private String book;
    private String user;
    private LocalDate reservedAt;
    private LocalDate reservedUntil;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
