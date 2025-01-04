package com.practice.demo.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.practice.demo.services.BookService;
import org.libraryapi.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class BooksDataFetcher {

    private final BookService bookService;

    @Autowired
    public BooksDataFetcher(BookService bookService) {
        this.bookService = bookService;
    }

    @DgsQuery
    public List<BookDto> books(@InputArgument String titleFilter) {
        List<BookDto> books = bookService.getAll();

        if (titleFilter == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getTitle().contains(titleFilter))
                .collect(Collectors.toList());
    }

    @DgsMutation
    public BookDto addBook(@InputArgument(name = "book") BookDto submittedBook) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(submittedBook.getTitle());
        bookDto.setPublicationYear(submittedBook.getPublicationYear());
        bookDto.setAuthor(submittedBook.getAuthor());
        bookDto.setIsbn(submittedBook.getIsbn());

        bookService.addBook(bookDto);

        return bookDto;
    }
}

