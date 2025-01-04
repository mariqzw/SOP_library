package com.practice.demo.services;

import java.util.List;
import java.util.UUID;
import org.libraryapi.dto.BookDto;

public interface BookService {
    void addBook(BookDto bookDto);
    BookDto findBookByTitle(String title);
    BookDto findBookById(UUID id);
    List<BookDto> getAll();
}
