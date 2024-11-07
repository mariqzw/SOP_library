package com.practice.demo.init;

import com.practice.demo.services.BookService;
import com.practice.demo.services.ReservationService;
import com.practice.demo.services.UserService;
import com.practice.demo.services.dtos.BookDto;
import com.practice.demo.services.dtos.ReservationDto;
import com.practice.demo.services.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private UserService userService;
    private BookService bookService;
    private ReservationService reservationService;

    public DataInitializer() {
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void run(String... args) throws Exception {
        createUsers();
        createBooks();
        createReservations();
    }

    private void createUsers() {
        UserDto user1 = new UserDto();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");
        user1.setName("John");
        user1.setSurname("Doe");
        userService.register(user1);

        UserDto user2 = new UserDto();
        user2.setUsername("jane_doe");
        user2.setEmail("jane.doe@example.com");
        user2.setName("Jane");
        user2.setSurname("Doe");
        userService.register(user2);

        UserDto user3 = new UserDto();
        user3.setUsername("martin_jonson");
        user3.setEmail("martin.j@example.com");
        user3.setName("Martin");
        user3.setSurname("Jonson");
        userService.register(user3);

        System.out.println("DataInitializer: Users have been added.");
    }

    private void createBooks() {
        BookDto book1 = new BookDto();
        book1.setTitle("Effective Java");
        book1.setAuthor("Joshua Bloch");
        book1.setPublicationYear(2008);
        book1.setIsbn("978-0134685991");
        bookService.addBook(book1);

        BookDto book2 = new BookDto();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setPublicationYear(2008);
        book2.setIsbn("978-0132350884");
        bookService.addBook(book2);

        BookDto book3 = new BookDto();
        book3.setTitle("Kotlin in Action");
        book3.setAuthor("Dmitry Jemerov");
        book3.setPublicationYear(2017);
        book3.setIsbn("978-1617293290");
        bookService.addBook(book3);

        BookDto book4 = new BookDto();
        book4.setTitle("Kotlin in Action");
        book4.setAuthor("Dmitry Jemerov");
        book4.setPublicationYear(2017);
        book4.setIsbn("978-1617293291");
        bookService.addBook(book4);

        System.out.println("DataInitializer: Books have been added.");
    }

    private void createReservations() {
        ReservationDto reservation1 = new ReservationDto();
        reservation1.setBook("Effective Java");
        reservation1.setUser("john_doe");
        reservationService.register(reservation1, "john_doe");

        ReservationDto reservation2 = new ReservationDto();
        reservation2.setBook("Clean Code");
        reservation2.setUser("jane_doe");
        reservationService.register(reservation2, "jane_doe");

        System.out.println("DataInitializer: Reservations have been added.");
    }
}
