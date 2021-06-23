package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DistrictService {
    District getDistrictById(Long districtId);
    District getDistrictByName(String name);
    Page<District> getAllDistricts(Pageable pageable);
    //Page<District> getAllDistrictsByDepartmentId(Long departmentId, Pageable pageable);
    //District createDistrict(District district, Long departmentId);
    District createDistrict(District district);
    ResponseEntity<?> deleteDistrict(Long districtId);
}
