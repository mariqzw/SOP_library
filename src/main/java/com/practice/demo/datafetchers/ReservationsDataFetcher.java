package com.practice.demo.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.practice.demo.services.BookService;
import com.practice.demo.services.ReservationService;
import com.practice.demo.services.UserService;
import org.libraryapi.dto.BookDto;
import org.libraryapi.dto.UserDto;
import org.libraryapi.dto.ReservationDto;
import org.libraryapi.dto.ShowReservationDto;
import org.libraryapi.dto.SubmittedReservationDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class ReservationsDataFetcher {

    private final ReservationService reservationService;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public ReservationsDataFetcher(ReservationService reservationService, BookService bookService, UserService userService) {
        this.reservationService = reservationService;
        this.bookService = bookService;
        this.userService = userService;
    }


    @DgsQuery
    public List<ShowReservationDto> reservations(@InputArgument String reservationFilter) {
        return reservationService.getAll();
    }

    @DgsMutation
    public ReservationDto addReservation(@InputArgument(name = "reservation") SubmittedReservationDto submittedReservationDto) {
        BookDto bookDto = bookService.findBookByTitle(submittedReservationDto.getBookTitle());
        UserDto userDto = userService.findByUsername(submittedReservationDto.getUsername());

        if (bookDto == null || userDto == null) {
            throw new RuntimeException("Книга или пользователь не найдены!");
        }

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setBook(bookDto.getTitle());
        reservationDto.setUser(userDto.getUsername());

        reservationService.register(reservationDto, userDto.getUsername());
        return reservationDto;
    }
}
