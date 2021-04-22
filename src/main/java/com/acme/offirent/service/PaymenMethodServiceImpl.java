package com.acme.offirent.service;

import com.acme.offirent.domain.model.PaymentMethod;
import com.acme.offirent.domain.model.Resource;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.PaymentMethodRepository;
import com.acme.offirent.domain.service.PaymentMethodService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymenMethodServiceImpl implements PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Page<PaymentMethod> GetAllByAccountId(Long accountId, Pageable pageable) {
        return paymentMethodRepository.findByAccountId(accountId, pageable)
                .orElseThrow( ()->new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public PaymentMethod getById(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow( ()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod, Long accountId) {
        return accountRepository.findById(accountId).map(account -> {
            paymentMethod.setAccount(account);
            return paymentMethodRepository.save(paymentMethod);
        }).orElseThrow( ()->new ResourceNotFoundException("Account","Id",accountId));

    }

    @Override
    public PaymentMethod updatePaymentMethod(Long paymentMethodId, PaymentMethod resourceRequest) {
        return paymentMethodRepository.findById(paymentMethodId).map(paymentMethod -> {
            paymentMethod.setCardNumber(resourceRequest.getCardNumber());
            paymentMethod.setCv(resourceRequest.getCv());
            paymentMethod.setDueDate(resourceRequest.getDueDate());
            paymentMethod.setOwnerName(resourceRequest.getOwnerName());
            return paymentMethodRepository.save(paymentMethod);
        }).orElseThrow( ()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
    }

    @Override
    public ResponseEntity<?> deletePaymentMethod(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId).map(paymentMethod -> {
            paymentMethodRepository.delete(paymentMethod);
            return ResponseEntity.ok().build();
        }).orElseThrow( ()->new ResourceNotFoundException("PaymentMethod","Id",paymentMethodId));
    }
}
