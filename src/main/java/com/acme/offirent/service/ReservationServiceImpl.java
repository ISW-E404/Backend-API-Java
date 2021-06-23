package com.acme.offirent.service;

import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.repository.ReservationRepository;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OfficeRepository officeRepository;

    public Page<Reservation> getAllReservations(Pageable pageable){ return reservationRepository.findAll(pageable);}

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow( ()->new ResourceNotFoundException("Office","Id",reservationId) );
    }

    @Override
    public Reservation getReservationByIdAndAccountId(Long accountId, Long reservationId) {
        return reservationRepository.findByIdAndAccountId(reservationId,accountId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Reservation not found with Id " + reservationId +
                                " and Account Id " + accountId));
    }



    @Override
    public Reservation getReservationByIdAndAccountEmail(String accountEmail, Long reservationId) {
        return reservationRepository.findByIdAndAccountEmail(reservationId,accountEmail)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Reservation not found with Id " + reservationId +
                                " and Account Email " + accountEmail));
    }

    @Override
    public Page<Reservation> getAllReservationsByAccountId(Long accountId, Pageable pageable) {
        return reservationRepository.findByAccountId(accountId,pageable);
    }

    @Override
    public Page<Reservation> getAllReservationsByAccountEmail(String accountEmail, Pageable pageable) {
        return reservationRepository.findByAccountEmail(accountEmail,pageable);
    }

    @Override
    public Page<Reservation> getAllReservationsByOfficeId(Long officeId, Pageable pageable) {
        return reservationRepository.findByOfficeId(officeId,pageable);
    }

    @Override
    public Reservation createReservationWithAccountId(Reservation reservation, Long accountId, Long officeId) {
        return accountRepository.findById(accountId).map(account -> {
            Office office = officeRepository.findById(officeId)
                    .orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
            reservation.setAccount(account);
            reservation.setOffice(office);
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public Reservation createReservationWithAccountEmail(Reservation reservation, String accountEmail, Long officeId) {
        return accountRepository.findByEmail(accountEmail).map(account -> {
            Office office = officeRepository.findById(officeId)
                    .orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
            reservation.setAccount(account);
            reservation.setOffice(office);
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Account","Email",accountEmail));
    }

    @Override
    public Reservation updateReservation(Long reservationId, Reservation reservationRequest) {
        return reservationRepository.findById(reservationId).map(reservation->{
            reservation.setInitialDate(reservationRequest.getInitialDate());
            reservation.setEndDate(reservationRequest.getEndDate());
            reservation.setStatus(reservationRequest.isStatus());

            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }

    @Override
    public ResponseEntity<?> deleteReservationWithAccountId(Long accountId, Long reservationId) {
        if(!accountRepository.existsById(accountId))
            throw new ResourceNotFoundException("Account","Id", accountId);

        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }


}
