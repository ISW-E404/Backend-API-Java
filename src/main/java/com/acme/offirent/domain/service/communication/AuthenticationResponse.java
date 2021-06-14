package com.acme.offirent.domain.service.communication;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthenticationResponse implements Serializable {

    private String username;
    private String token;
}
