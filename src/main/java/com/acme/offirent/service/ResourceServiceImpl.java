package com.acme.offirent.service;
import com.acme.offirent.domain.model.Resource;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.repository.ResourceRepository;
import com.acme.offirent.domain.service.ResourceService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {
    
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public Page<Resource> getAllResources(Pageable pageable){
        return resourceRepository.findAll(pageable);
    }

    @Override
    public Resource getResourceById  (Long resourceId){
        return resourceRepository.findById(resourceId)
            .orElseThrow(()->
            new ResourceNotFoundException("Resource","Id","resourceId"));
    }
    
    //repositorymethods
    @Override
    public Page<Resource> findByOfficeId(Long officeId, Pageable pageable){
        return resourceRepository.findByOfficeId(officeId, pageable);
    } 

    @Override
    public Resource findByNameAndOfficeId(String resourceName, Long officeId){
        return resourceRepository.findByNameAndOfficeId(resourceName, officeId).orElseThrow(()->
                new ResourceNotFoundException("Resource","Name","resourceName"));
    }

    @Override
    public Resource createResource(Long officeId,Resource resource){
        return  officeRepository.findById(officeId).map(office -> {
            resource.setOffice(office);
            return resourceRepository.save(resource);
        }).orElseThrow(()-> new ResourceNotFoundException("Office","Id",officeId));

    }

    @Override
    public Resource updateResource(Long officeId, Long resourceId,Resource resourceRequest){
        if(!officeRepository.existsById(officeId))
            throw new ResourceNotFoundException("Resource","Id","resourceId");
        //else (preguntar)
        return resourceRepository.findById(resourceId).map(resource->{
            resource.setName(resourceRequest.getName());
            resource.setComment(resourceRequest.getComment());
            resource.setQuantity(resourceRequest.getQuantity());
            return resourceRepository.save(resource);
        })
        .orElseThrow(()->
        new ResourceNotFoundException("Resource","Id","resourceId"));
    }

    @Override
    public ResponseEntity<?> deleteResource(Long officeId,Long resourceId){
        if(!officeRepository.existsById(officeId))
            throw new ResourceNotFoundException("Resource","Id","resourceId");
         //else (preguntar)    
        return resourceRepository.findById(resourceId).map(resource->{
            resourceRepository.delete(resource);
            return ResponseEntity.ok().build();
        })
        .orElseThrow(()->
        new ResourceNotFoundException("Resource","Id","resourceId"));
    }


}