package com.practice.demo.services.impl;

import com.practice.demo.models.Reservation;
import com.practice.demo.repositories.BookRepository;
import com.practice.demo.repositories.ReservationRepository;
import com.practice.demo.repositories.UserRepository;
import com.practice.demo.services.ReservationService;
import com.practice.demo.services.dtos.ReservationDto;
import com.practice.demo.services.dtos.ShowReservationDto;
import com.practice.demo.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    public ReservationServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void register(ReservationDto reservationDto, String username) {
        if (!this.validationUtil.isValid(reservationDto)) {
            this.validationUtil
                    .violations(reservationDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        reservationDto.setReservedAt(LocalDateTime.now());;
        reservationDto.setReservedUntil(LocalDateTime.now().plusDays(14));

        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);

        reservation.setBook(bookRepository.findByTitle(reservationDto.getBook()).orElse(null));
        reservation.setUser(userRepository.findByUsername(username).orElse(null));

        reservationRepository.saveAndFlush(reservation);
    }

    @Override
    public List<ShowReservationDto> findAllReservationsByUsername(String username) {
        return this.reservationRepository
                .findAllByUserUsername(username)
                .stream()
                .map(reservation -> modelMapper.map(reservation, ShowReservationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowReservationDto> findAllReservationsByUser(UUID userId) {
        return this.reservationRepository
                .findAllByUserId(userId)
                .stream()
                .map(reservation -> modelMapper.map(reservation, ShowReservationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowReservationDto> getAll() {
        return reservationRepository.findAll()
                .stream()
                .map((reservation) -> modelMapper.map(reservation, ShowReservationDto.class))
                .collect(Collectors.toList());
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}