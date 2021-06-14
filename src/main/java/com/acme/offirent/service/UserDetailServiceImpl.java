package com.acme.offirent.service;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.service.DefaultUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements DefaultUserDetailService {

    @Autowired
    private AccountRepository accountRepository;
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String inputUsername) throws UsernameNotFoundException {
        Account existingUser = accountRepository.findByEmail(inputUsername);
        String password = passwordEncoder.encode(existingUser.getPassword());

        if(existingUser.getEmail().equals(inputUsername)) {
            return new User(existingUser.getEmail(), password, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException(String.format("User not found with username %s", inputUsername));
    }
}
