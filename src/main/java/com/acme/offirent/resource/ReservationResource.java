package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationResource {

    private Long id;
    private boolean status;
    private Date initialDate;
    private Date endDate;


}
