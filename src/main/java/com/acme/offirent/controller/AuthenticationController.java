package com.acme.offirent.controller;

import com.acme.offirent.domain.service.DefaultUserDetailsService;
import com.acme.offirent.domain.service.communication.AuthenticationRequest;
import com.acme.offirent.domain.service.communication.AuthenticationResponse;
import com.acme.offirent.util.JwtCenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/accounts/authenticate")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCenter tokenCenter;
    @Autowired
    private DefaultUserDetailsService userDetailsService;


    @Operation(summary = "Sign in Account ",description = "Sign in with an existing Account",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sign in with an existing Account with given Username and Password",content =@Content(mediaType = "application/json") )
    })
    @PostMapping
    public ResponseEntity<?> generateAuthenticationToken(
            @RequestBody AuthenticationRequest request)
            throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        System.out.println("Password: " + request.getPassword());
        String token = tokenCenter.generateToken(userDetails);
        return ResponseEntity.ok(
                new AuthenticationResponse(userDetails.getUsername(), token));
    }








    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
