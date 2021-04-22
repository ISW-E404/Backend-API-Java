package com.acme.offirent.service;

import com.acme.offirent.domain.model.District;
import com.acme.offirent.domain.repository.DepartmentRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.service.DistrictService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public District getDistrictById(Long districtId) {
        return districtRepository.findById(districtId)

                .orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
    }

    @Override
    public Page<District> getAllDistricts(Pageable pageable) {
        return districtRepository.findAll(pageable);
    }


    @Override
    public Page<District> getAllDistrictsByDepartmentId(Long departmentId, Pageable pageable) {
        if(!departmentRepository.existsById(departmentId))
            throw  new ResourceNotFoundException("Department","Id",departmentId);
        return districtRepository.findAllByDepartmentId(departmentId,pageable);
    }

    @Override
    public District createDistrict(District district,Long departmentId) {
        return departmentRepository.findById(departmentId).map(department -> {
            district.setDepartment(department);
            return districtRepository.save(district);
        }).orElseThrow(()->new ResourceNotFoundException("Department","Id",departmentId));
    }

    @Override
    public ResponseEntity<?> deleteDistrict(Long districtId) {
        return districtRepository.findById(districtId).map(district -> {
            districtRepository.delete(district);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
    }
}
