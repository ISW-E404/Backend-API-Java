package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Department;
import com.acme.offirent.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long departmentId);
    Page<Department> getAllDepartments(Pageable pageable);

    Department createDepartment(Department department);
    ResponseEntity<?> deleteDepartment(Long departmentId);
}
