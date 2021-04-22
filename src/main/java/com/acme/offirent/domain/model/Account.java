package com.acme.offirent.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String identification;

    @NotNull
    private Long accType;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Long phone;

    @NotNull
    private boolean premium;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "discount_id",nullable = true)
    @JsonIgnore
    private Discount discount;
}