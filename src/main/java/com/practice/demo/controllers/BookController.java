package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.BookAssembler;
import com.practice.demo.hateoas.model.BookModel;
import com.practice.demo.models.Book;
import com.practice.demo.services.BookService;
import com.practice.demo.services.dtos.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    private BookAssembler bookAssembler;

    @Autowired
    private void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    private void setBookAssembler(BookAssembler bookAssembler) {
        this.bookAssembler = bookAssembler;
    }

    @PostMapping("/add")
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    @GetMapping("/all")
    public CollectionModel<BookModel> getAllBooks() {
        return bookAssembler.toCollectionModel(bookService.getAll());
    }

    @GetMapping("/id/{id}")
    public BookModel findBookById(@PathVariable UUID id) {
        return bookAssembler.toModel(bookService.findBookById(id));
    }

    @GetMapping("/title/{title}")
    public BookModel findBookByTitle(@PathVariable String title) {
        return bookAssembler.toModel(bookService.findBookByTitle(title));
    }
}
