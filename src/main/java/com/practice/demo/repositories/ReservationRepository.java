package com.practice.demo.repositories;

import com.practice.demo.models.Reservation;
import com.practice.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findAllByUserId(UUID userId);
    Optional<Reservation> findAllByUserUsername(String username);
}
