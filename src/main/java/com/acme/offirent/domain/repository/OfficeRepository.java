package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
       Page<Office> findAllByDistrictId (Long districtId, Pageable pageable);
       Optional<Page<Office>> findByPriceLessThanEqual (float price, Pageable pageable);
       List<Office> findAllByAccountId(Long accountId);
       Page<Office> findAllByAccountId(Long accountId,Pageable pageable);
       Optional<Page<Office>> findAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(float price1, float price2, Pageable pageable);
}
