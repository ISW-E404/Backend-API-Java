package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SaveReservationResource {

    @NotNull
    private Date initialDate;

    @NotNull
    private Date endDate;
}
