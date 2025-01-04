package com.practice.demo.controllers;

import com.practice.demo.hateoas.assembler.BookAssembler;
import com.practice.demo.services.BookService;
import org.libraryapi.controllers.BookApi;
import org.libraryapi.dto.BookDto;
import org.libraryapi.model.BookModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController implements BookApi {

    private BookService bookService;

    private BookAssembler bookAssembler;
    private ModelMapper modelMapper;

    @Autowired
    private void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    private void setBookAssembler(BookAssembler bookAssembler) {
        this.bookAssembler = bookAssembler;
    }

    @Override
    @PostMapping("/add")
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    @Override
    @GetMapping("/all")
    public CollectionModel<BookModel> getAllBooks() {
        return bookAssembler.toCollectionModel(bookService.getAll());
    }

    @Override
    @GetMapping("/id/{id}")
    public BookModel findBookById(@PathVariable UUID id) {
        return bookAssembler.toModel(bookService.findBookById(id));
    }

    @Override
    @GetMapping("/title/{title}")
    public BookModel findBookByTitle(@PathVariable String title) {
        return bookAssembler.toModel(bookService.findBookByTitle(title));
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
