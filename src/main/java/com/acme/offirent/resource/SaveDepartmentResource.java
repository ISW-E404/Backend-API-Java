package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveDepartmentResource {
    @NotNull
    @Size(max = 100)
    private String name;
}
