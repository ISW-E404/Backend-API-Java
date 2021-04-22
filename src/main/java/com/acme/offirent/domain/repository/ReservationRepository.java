package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByIdAndAccountId(Long id, Long accountId);
    Page<Reservation> findByAccountId(Long accountId, Pageable pageable);
    Page<Reservation> findByOfficeId(Long officeId, Pageable pageable);
}
