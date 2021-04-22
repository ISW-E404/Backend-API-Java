package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService{
    Page <Account> getAllAccounts(Pageable pageable);
    Account getAccountById (Long accountId);
    //Page <Account> getAllAccountsByReservationId(Long reservationId, Pageable pageable);
    //Page <Account> getAllAccountsByOfficeId(Long officeId, Pageable pageable);

    Account createAccount(Account account);
    Account updateAccount(Long accountId, Account accountRequest);
    ResponseEntity<?> deleteAccount(Long accountId);
}