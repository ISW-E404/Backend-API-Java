package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
       Page<Office> findAllByDistrictId (Long districtId, Pageable pageable);


       @Query("select o from Office o where o.price <= ?1")
       Optional<Page<Office>> findByPriceLessThanEqual (float price, Pageable pageable);

       List<Office> findAllByAccountId(Long accountId);  // solo para calcular cantidad de oficinas: lista.size()
       Page<Office> findAllByAccountId(Long accountId,Pageable pageable);

       List<Office> findAllByAccountEmail(String accountEmail);
       Page<Office> findAllByAccountEmail(String accountEmail, Pageable pageable);

       @Query("select o from Office o where o.price between ?1 and ?2")
       Optional<Page<Office>> findAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(float price1, float price2, Pageable pageable);

       @Query(value="select o from offices o order by o.score DESC LIMIT 5", nativeQuery = true)
       Page<Office> findOfficesOrderByScoreDescFirst5 (Pageable pageable);
}
