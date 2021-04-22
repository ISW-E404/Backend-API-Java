package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveOfficeResource {

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    private Long floor;

    @NotNull
    private Long capacity;

    @NotNull
    private boolean allowResource;

    @NotNull
    private float score;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private float price;

    @NotNull
    private boolean status;


    @NotNull
    @Size(max = 250)
    private String comment;
}
