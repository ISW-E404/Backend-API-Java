package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService{
    Page <Account> getAllAccounts(Pageable pageable);
    Account getAccountById (Long accountId);
    Account getAccountByEmail (String email);
    //Page <Account> getAllAccountsByReservationId(Long reservationId, Pageable pageable);
    //Page <Account> getAllAccountsByOfficeId(Long officeId, Pageable pageable);

    Account createAccount(Account account);
    Account updateAccountById(Long accountId, Account accountRequest);
    Account updateAccountByEmail(String email, Account accountRequest);
    ResponseEntity<?> deleteAccountById(Long accountId);
    ResponseEntity<?> deleteAccountByEmail(String email);
}