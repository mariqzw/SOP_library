package com.practice.demo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {
    @ManyToOne(optional = false)
    private Book book;
    @ManyToOne(optional = false)
    private User user;
    private LocalDateTime reservedAt;
    private LocalDateTime reservedUntil;

    protected Reservation() {};

    public Reservation(Book book, User user, LocalDateTime reservedAt, LocalDateTime reservedUntil) {
        this.book = book;
        this.user = user;
        this.reservedAt = reservedAt;
        this.reservedUntil = reservedUntil;
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

    @Column(name = "reservedAtTime", nullable = false)
    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    @Column(name = "reservedUntilTime", nullable = false)
    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
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
