package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    Page<Discount> getAllDiscounts(Pageable pageable);
    Discount getDiscountById  (Long discountId);
    
    //repositorymethods
    //Discount findByAccountIdAndDiscountId(Long accountId, Long discountId);

    //crud?
    Discount createDiscount(Discount discount);
    Discount updateDiscount(Long discountId,Discount discountRequest);
    ResponseEntity<?> deleteDiscount(Long discountId);

}