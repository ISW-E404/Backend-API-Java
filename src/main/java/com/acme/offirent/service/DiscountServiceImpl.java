package com.acme.offirent.service;
import com.acme.offirent.domain.model.Discount;
import com.acme.offirent.domain.repository.DiscountRepository;
import com.acme.offirent.domain.service.DiscountService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;
    

    @Override
    public Page<Discount> getAllDiscounts(Pageable pageable){
        return discountRepository.findAll(pageable);

    }
    
    @Override
    public Discount getDiscountById(Long discountId){
        return discountRepository.findById(discountId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Resource","Id","resourceId"));
    }
    
    
    //public Discount findByAccountIdAndDiscountId(Long accountId, Long discountId){}

    //crud?
    @Override
    public Discount createDiscount(Discount discount){
        return discountRepository.save(discount);
    }
    
    @Override
    public Discount updateDiscount(Long discountId,Discount discountRequest){
        return discountRepository.findById(discountId).map(discount->{
            discount.setDescription(discountRequest.getDescription());
            discount.setPercentage(discountRequest.getPercentage());

            return discountRepository.save(discount);
        })
        .orElseThrow(()->
        new ResourceNotFoundException("Discount","Id","discountId"));
            
    }
    
    @Override
    public ResponseEntity<?> deleteDiscount(Long discountId){
        return discountRepository.findById(discountId).map(discount->{
            discountRepository.delete(discount);
            return ResponseEntity.ok().build();
        })
        .orElseThrow(()->
        new ResourceNotFoundException("Discount","Id","discountId"));
    }
   
}