package com.practice.demo.services.impl;

import com.practice.demo.models.Book;
import com.practice.demo.repositories.BookRepository;
import com.practice.demo.services.BookService;
import com.practice.demo.services.dtos.BookDto;
import com.practice.demo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public BookServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addBook(BookDto bookDto) {
        if (!this.validationUtil.isValid(bookDto)) {

            this.validationUtil
                    .violations(bookDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        if (bookRepository.findByIsbn(bookDto.getIsbn()).isEmpty()) {
            bookRepository.saveAndFlush(modelMapper.map(bookDto, Book.class));
        }

    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .map(book -> modelMapper.map(book, BookDto.class))
                .orElse(null);
    }

    @Override
    public BookDto findBookById(UUID id) {
        return bookRepository.findById(id)
                .map(book -> modelMapper.map(book, BookDto.class))
                .orElse(null);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
