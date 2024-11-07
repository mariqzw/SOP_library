package com.practice.demo.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

public class BookModel extends RepresentationModel<BookModel> {
    private String title;
    private int publicationYear;
    private String author;

    protected BookModel() {};

    public BookModel(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

}
