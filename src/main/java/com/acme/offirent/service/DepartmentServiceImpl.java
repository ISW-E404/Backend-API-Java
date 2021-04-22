package com.acme.offirent.service;

import com.acme.offirent.domain.model.Department;
import com.acme.offirent.domain.repository.DepartmentRepository;
import com.acme.offirent.domain.service.DepartmentService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(()->new ResourceNotFoundException("Department","Id",departmentId));
    }

    @Override
    public Page<Department> getAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public ResponseEntity<?> deleteDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId).map(department -> {
            departmentRepository.delete(department);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Department","Id",departmentId));
    }
}
