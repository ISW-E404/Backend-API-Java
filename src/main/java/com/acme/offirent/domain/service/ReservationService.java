package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    Reservation getReservationById(Long reservationId);
    Page<Reservation> getAllReservations(Pageable pageable);

    Reservation getReservationByIdAndAccountId(Long accountId, Long reservationId);
    Reservation getReservationByIdAndAccountEmail(String accountEmail, Long reservationId);

    Page<Reservation> getAllReservationsByAccountId(Long accountId, Pageable pageable);
    Page<Reservation> getAllReservationsByAccountEmail(String accountEmail, Pageable pageable);

    Page<Reservation> getAllReservationsByOfficeId(Long officeId, Pageable pageable);
    Reservation createReservationWithAccountId(Reservation reservation, Long accountId, Long officeId);
    Reservation createReservationWithAccountEmail(Reservation reservation, String accountEmail, Long officeId);

    Reservation updateReservation(Long reservationId, Reservation reservationRequest);
    ResponseEntity<?> deleteReservationWithAccountId(Long reservationId, Long accountId);
    ResponseEntity<?> deleteReservation(Long reservationId);

}
