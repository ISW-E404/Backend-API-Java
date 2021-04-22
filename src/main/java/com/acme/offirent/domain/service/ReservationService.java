package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    Reservation getReservationByIdAndAccountId(Long accountId, Long reservationId);
    Page<Reservation> getAllReservationsByAccountId(Long accountId, Pageable pageable);
    Page<Reservation> getAllReservationsByOfficeId(Long officeId, Pageable pageable);
    Reservation createReservation(Reservation reservation, Long accountId, Long officeId);
    Reservation updateReservation(Long reservationId, Reservation reservationRequest);
    ResponseEntity<?> deleteReservation(Long reservationId, Long accountId);

}
