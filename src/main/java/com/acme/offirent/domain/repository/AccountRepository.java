package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}