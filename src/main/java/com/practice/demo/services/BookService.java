package com.practice.demo.services;

import com.practice.demo.services.dtos.BookDto;

import java.util.List;
import java.util.UUID;

public interface BookService {
    void addBook(BookDto bookDto);
    BookDto findBookByTitle(String title);
    BookDto findBookById(UUID id);
    List<BookDto> getAll();
}
