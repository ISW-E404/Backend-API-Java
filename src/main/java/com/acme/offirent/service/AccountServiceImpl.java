package com.acme.offirent.service;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account accountRequest) {
        return accountRepository.save(accountRequest);
        //TODO
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long accountId) {
        return accountRepository.findById(accountId).map(account->{
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        })
                .orElseThrow(()->
                        new ResourceNotFoundException("Account","Id",accountId));
    }
}
