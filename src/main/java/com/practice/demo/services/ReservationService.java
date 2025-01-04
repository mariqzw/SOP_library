package com.practice.demo.services;

import java.util.List;
import java.util.UUID;
import org.libraryapi.dto.ReservationDto;
import org.libraryapi.dto.ShowReservationDto;

public interface ReservationService {

    void register(ReservationDto reservationDto, String username);

    List<ShowReservationDto> getAll();

    List<ShowReservationDto> findAllReservationsByUser(UUID userId);

    List<ShowReservationDto> findAllReservationsByUsername(String username);
}
