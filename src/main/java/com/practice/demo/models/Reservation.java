package com.practice.demo.models;

import com.practice.demo.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {
    @ManyToOne(optional = false)
    private Book book;
    @ManyToOne(optional = false)
    private User user;
    private LocalDate reservedAt;
    private LocalDate reservedUntil;
    private Status status;

    protected Reservation() {};

    public Reservation(Book book, User user, LocalDate reservedAt, LocalDate reservedUntil, Status status) {
        this.book = book;
        this.user = user;
        this.reservedAt = reservedAt;
        this.reservedUntil = reservedUntil;
        this.status = status;
    }

    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable=false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "reserved_at", nullable = false)
    public LocalDate getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDate reservedAt) {
        this.reservedAt = reservedAt;
    }

    @Column(name = "reserved_until", nullable = false)
    public LocalDate getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDate reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "book=" + book +
                ", user=" + user +
                ", reservedAt=" + reservedAt +
                ", reservedUntil=" + reservedUntil +
                '}';
    }
}
