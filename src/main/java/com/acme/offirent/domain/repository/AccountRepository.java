package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail (String email);
}