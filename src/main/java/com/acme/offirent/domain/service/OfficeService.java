package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfficeService {
    Page<Office> getAllOffices(Pageable pageable);
    Office getOfficeById  (Long officeId);
    Page<Office> getAllOfficesByDistrictId(Long districtId, Pageable pageable);
    Page<Office> getAllOfficesByPriceLessThanEqual (float price, Pageable pageable);
    Page<Office> getAllOfficesByAccountId(Long accountId, Pageable pageable);
    Page<Office> getAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(float price1, float price2, Pageable pageable);
    Office activeOffice(Long accountId, Long officeId);
    Office createOffice(Office office, Long accountId, Long districtId);
    Office rateOffice(Long officeId, Office officeRequest);
    Office updateOffice(Long officeId,Office officeRequest);
    ResponseEntity<?> deleteOffice(Long officeId);

}