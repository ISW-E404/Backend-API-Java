package com.acme.offirent;


import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.service.AccountServiceImpl;
import com.acme.offirent.service.OfficeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;


    @TestConfiguration
    static class OfficeServiceTestConfiguration{

        @Bean
        public AccountService accountService(){
            return new AccountServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetAccountById With Valid Id Then Returns Account")
    public void whenGetOfficeByIdWithValidIdThenReturnsOffice(){

        //Arrange
        Long id = 1L;
        Account account= new Account();
        account.setId(id);

        when(accountRepository.findById(account.getId()))
                .thenReturn(Optional.of(account));


        //Act
        Account searchedAccount = accountService.getAccountById(id);

        //Assert
        assertThat(searchedAccount.getId()).isEqualTo(id);
    }
}
