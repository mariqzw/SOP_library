package com.practice.demo.services;

import com.practice.demo.services.dtos.ReservationDto;
import com.practice.demo.services.dtos.ShowReservationDto;
import com.practice.demo.services.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    void register(ReservationDto reservationDto, String username);

    List<ShowReservationDto> getAll();

    List<ShowReservationDto> findAllReservationsByUser(UUID userId);

    List<ShowReservationDto> findAllReservationsByUsername(String username);
}
