//TODO
//vere que hay de comer me avisas al wtsp
package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}