package com.acme.offirent.service;

import com.acme.offirent.domain.model.District;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.exception.LockedActionException;
import com.acme.offirent.exception.ResourceConditionException;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Office> getAllOffices(Pageable pageable) {
        return officeRepository.findAll(pageable);
    }

    @Override
    public Office getOfficeById(Long officeId) {
        return officeRepository.findById(officeId)
                .orElseThrow( ()->new ResourceNotFoundException("Office","Id",officeId) );
    }

    @Override
    public Page<Office> getAllOfficesByDistrictId(Long districtId, Pageable pageable) {
        if(!districtRepository.existsById(districtId))
            throw new ResourceNotFoundException("District","Id",districtId);
        return officeRepository.findAllByDistrictId(districtId,pageable);
    }

    @Override
    public Page<Office> getAllOfficesByPriceLessThanEqual(float price, Pageable pageable) {
        return officeRepository.findByPriceLessThanEqual(price, pageable)
                .orElseThrow( ()->new ResourceNotFoundException("Office","Price",price) );
    }

    @Override
    public Page<Office> getAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(float price1, float price2, Pageable pageable) {
        return officeRepository.findAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(price1, price2, pageable)
                .orElseThrow( ()->new ResourceNotFoundException("Resources offices not found between" + price1 +"and" + price2 + "prices") );
    }

    @Override
    public Office activeOffice(Long accountId, Long officeId) {
        if(accountRepository.findById(accountId).isEmpty())
            throw new ResourceNotFoundException("Account","Id",accountId);


        if(!accountRepository.findById(accountId).get().isPremium())
            throw new ResourceConditionException("Office","status","activated");

        return officeRepository.findById(officeId).map(office -> {
            office.setStatus(true);
            return officeRepository.save(office);
        }).orElseThrow(()-> new ResourceNotFoundException("Office","Id",officeId));
    }

    @Override
    public Page<Office> getAllOfficesByAccountId(Long accountId, Pageable pageable){
        if(!accountRepository.existsById(accountId))
            throw new ResourceNotFoundException("Account","Id",accountId);
        return  officeRepository.findAllByAccountId(accountId,pageable);
    }

    @Override
    public Office createOffice(Office office, Long accountId, Long districtId){
        return accountRepository.findById(accountId).map(account -> {
            int Quantity = officeRepository.findAllByAccountId(accountId).size();
            if (Quantity <=15 || account.isPremium()) {
                District district = districtRepository.findById(districtId)
                        .orElseThrow( ()->new ResourceNotFoundException("District","Id",districtId) );
                office.setDistrict(district);
                office.setAccount(account);
                return officeRepository.save(office);
            }
            else
                throw new LockedActionException("Cant create an Office due to user is not premium and cant have more than 15 offices");
        }).orElseThrow( ()->new ResourceNotFoundException("Account","Id",accountId) );

    }

    @Override
    public Office rateOffice(Long officeId, Office officeRequest) {
        return officeRepository.findById(officeId).map(office -> {
            office.setScore(officeRequest.getScore());
            return officeRepository.save(office);
        }).orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
    }

    @Override
    public Office updateOffice(Long officeId, Office officeRequest) {
        return officeRepository.findById(officeId).map(office->{
            office.setAddress(officeRequest.getAddress());
            office.setFloor(officeRequest.getFloor());
            office.setCapacity(officeRequest.getCapacity());
            office.setAllowResource(officeRequest.isAllowResource());
            office.setScore(officeRequest.getScore());
            office.setDescription(officeRequest.getDescription());
            office.setPrice(officeRequest.getPrice());
            office.setStatus(officeRequest.isStatus());
            office.setComment(officeRequest.getComment());
            return officeRepository.save(office);
        }).orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
    }

    @Override
    public ResponseEntity<?> deleteOffice(Long officeId) {
        return officeRepository.findById(officeId).map(office -> {
            officeRepository.delete(office);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Office","Id",officeId));
    }
}