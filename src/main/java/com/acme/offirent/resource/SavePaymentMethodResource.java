package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePaymentMethodResource {
    @NotNull
    private String cardNumber;

    @NotNull
    private String ownerName;

    @NotNull
    private String dueDate;

    @NotNull
    private String cv;
}
