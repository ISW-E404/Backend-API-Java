package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountResource {

    private Long id;

    private String description;

    private float percentage;
}