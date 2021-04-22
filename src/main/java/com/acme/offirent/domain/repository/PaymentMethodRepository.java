package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.PaymentMethod;
import com.acme.offirent.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<Page<PaymentMethod>> findByAccountId(Long accountId, Pageable pageable);
}
