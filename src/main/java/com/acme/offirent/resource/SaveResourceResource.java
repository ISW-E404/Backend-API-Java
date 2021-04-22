package com.acme.offirent.resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveResourceResource {

    @NotNull
    private String name;

    @NotNull
    private long quantity;

    @Lob
    @NotNull
    private String comment;

}
