package com.acme.offirent.resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveDiscountResource {

    @Lob
    @NotNull
    private String description;

    @NotNull
    private float percentage;
}