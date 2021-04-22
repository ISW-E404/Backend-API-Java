package com.acme.offirent.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodResource {
    private Long id;
    
    private String cardNumber;
    
    private String ownerName;
    
    private String dueDate;
    
    private String cv;
}
