package com.practice.demo.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String title;
    private int publicationYear;
    private String author;
    private String isbn;

    protected Book() {};

    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "publication_year", nullable = false)
    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Column(name = "isbn", nullable = false, unique = true)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Override
    public String toString() {
        return "Book {" +
                "title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
