package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Department;
import com.acme.offirent.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //Page<Departament> findByCountryId (Long countryId, Pageable pageable);
}
