package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ResourceService {
    Page<Resource> getAllResources(Pageable pageable);
    Resource getResourceById  (Long resourceId);
    
    //repositorymethods
    Page<Resource> findByOfficeId(Long officeId, Pageable pageable); // buscar por id oficina
    Resource findByNameAndOfficeId(String officeName, Long officeId); // buscar implemento por nombre y id de oficina

    //crud?
    Resource createResource(Long officeId,Resource resource);
    Resource updateResource(Long officeId,Long resourceId,Resource resourceRequest);
    ResponseEntity<?> deleteResource(Long officeId,Long resourceId);

}
