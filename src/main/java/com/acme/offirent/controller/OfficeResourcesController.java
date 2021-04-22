package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.Resource;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.domain.service.ResourceService;
import com.acme.offirent.resource.OfficeResource;
import com.acme.offirent.resource.ResourceResource;
import com.acme.offirent.resource.SaveOfficeResource;
import com.acme.offirent.resource.SaveResourceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OfficeResourcesController {


    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get resource by Office",description = "Get resources by a given office",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get resources by a given office",content =@Content(mediaType = "application/json") )
                })
    @GetMapping("/offices/{id}/resources")
    public List<ResourceResource> getResourcesByOfficeId(@PathVariable(name = "id") Long officeId, Pageable pageable){
        Page<Resource> resourcePage = resourceService.findByOfficeId(officeId, pageable);
        List<ResourceResource> resources = resourcePage.getContent()
        .stream().map(this::convertToResource).collect(Collectors.toList());

        //return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }


    @Operation(summary = "Get resource by Name and Office",description = "Get resource by a given name and office",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get resource by a given name and office",content =@Content(mediaType = "application/json") )
                })
    @GetMapping("/offices/{id}/resources/{name}")
    public ResourceResource getResourceByNameAndOfficeId(@PathVariable(name = "id") Long officeId,@PathVariable(name = "name") String resourceName){
        return convertToResource(resourceService.findByNameAndOfficeId(resourceName, officeId)); 
        
    }


    @Operation(summary = "Create Resource ",description = "Create a new Resource",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new Resource for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/offices/{officeId}/resources")
    public ResourceResource createResource(@PathVariable(name = "officeId") Long officeId,@Valid @RequestBody SaveResourceResource resource){
        return convertToResource(
                resourceService.createResource(officeId,convertToEntity(resource)));
    }

    @Operation(summary = "Update Resource",description = "Update Resource for given Id",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update information of resource for given Id",content =@Content(mediaType = "application/json") )
    })
    @PutMapping("/offices/{officeId}/resources/{resourceId}")
    public ResourceResource updateResource(@PathVariable(name = "officeId") Long officeId,@PathVariable(name = "resourceId") Long resourceId,@Valid @RequestBody SaveResourceResource resource){
        return convertToResource(resourceService.updateResource(officeId,resourceId,convertToEntity(resource)));
    }

    @Operation(summary = "Delete Resources",description = "Delete Resource for given Id",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete resource for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/offices/{officeId}/resources/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable(name = "officeId") Long officeId, @PathVariable(name = "resourceId") Long resourceId){
        return resourceService.deleteResource(officeId,resourceId);
    }

    private Resource convertToEntity(SaveResourceResource resource){return  mapper.map(resource, Resource.class);}

    private ResourceResource convertToResource(Resource entity){return  mapper.map(entity,ResourceResource.class);}
}