package com.acme.offirent.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="payment_methods")
public class PaymentMethod{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cardNumber;

    @NotNull
    private String ownerName;

    @NotNull
    private String dueDate;

    @NotNull
    private String cv;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "account_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;
    
}