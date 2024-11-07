package com.practice.demo.hateoas.assembler;

import com.practice.demo.controllers.BookController;
import com.practice.demo.hateoas.model.BookModel;
import com.practice.demo.services.dtos.BookDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookAssembler extends RepresentationModelAssemblerSupport<BookDto, BookModel> {

    public BookAssembler() {
        super(BookController.class, BookModel.class);
    }


    @Override
    @NonNull
    public BookModel toModel(@NonNull BookDto entity) {
        BookModel model = createModelWithId(entity.getId(), entity);
        model.add(linkTo(methodOn(BookController.class)
                .getAllBooks())
                .withRel("books"));

        model.setTitle(entity.getTitle());
        model.setAuthor(entity.getAuthor());
        model.setPublicationYear(entity.getPublicationYear());

        return model;
    }
}
