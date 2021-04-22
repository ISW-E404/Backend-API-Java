package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveAccountResource {

    @NotNull
    @Size(max = 30)
    private String email;

    @NotNull
    @Size(max = 30)
    private String password;

    @NotNull
    private String identification;

    @NotNull
    private Long accType;

    @NotNull
    @Size(max = 30)
    private String firstName;

    @NotNull
    @Size(max = 30)
    private String lastName;

    @NotNull
    private Long phone;

    @NotNull
    private boolean premium;
}
