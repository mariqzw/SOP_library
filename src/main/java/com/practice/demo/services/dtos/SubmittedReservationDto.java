package com.practice.demo.services.dtos;

import java.time.LocalDateTime;

public class SubmittedReservationDto {
    private String bookTitle;
    private String username;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

