package com.acme.offirent.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
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
    private String description;

    @NotNull
    private float price;

    @NotNull
    private boolean status;

    @NotNull
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district_id")
    @JsonIgnore
    private District district;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

}
