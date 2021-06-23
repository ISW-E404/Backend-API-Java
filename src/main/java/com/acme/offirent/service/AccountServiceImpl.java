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
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Account","Email",email));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccountById(Long accountId, Account accountRequest) {
        return accountRepository.findById(accountId).map(account->{
                account.setEmail(accountRequest.getEmail());
                account.setFirstName(accountRequest.getFirstName());
                account.setLastName(accountRequest.getLastName());
                account.setIdentification(accountRequest.getIdentification());
                account.setPassword(accountRequest.getPassword());
                account.setPhone(accountRequest.getPhone());
                return  accountRepository.save(account);
        }).orElseThrow(()-> new ResourceNotFoundException("Account","Id",accountId));

    }

    @Override
    public Account updateAccountByEmail(String email, Account accountRequest) {
        return accountRepository.findByEmail(email).map(account->{
            account.setEmail(accountRequest.getEmail());
            account.setFirstName(accountRequest.getFirstName());
            account.setLastName(accountRequest.getLastName());
            account.setIdentification(accountRequest.getIdentification());
            account.setPassword(accountRequest.getPassword());
            account.setPhone(accountRequest.getPhone());
            return  accountRepository.save(account);
        }).orElseThrow(()-> new ResourceNotFoundException("Account","Email",email));
    }


    @Override
    public ResponseEntity<?> deleteAccountById(Long accountId) {
        return accountRepository.findById(accountId).map(account->{
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        })
                .orElseThrow(()->
                        new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public ResponseEntity<?> deleteAccountByEmail(String email) {
        return accountRepository.findByEmail(email).map(account->{
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        })
                .orElseThrow(()->
                        new ResourceNotFoundException("Account","Email",email));
    }
}
