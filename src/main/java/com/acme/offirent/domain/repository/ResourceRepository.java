package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findByOfficeId(Long officeId, Pageable pageable);
    Optional<Resource> findByNameAndOfficeId(String Name, Long officeId);

}