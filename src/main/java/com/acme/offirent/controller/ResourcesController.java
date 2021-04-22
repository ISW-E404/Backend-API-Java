package com.acme.offirent.controller;
import java.util.stream.Collectors;
import com.acme.offirent.domain.model.Resource;
import com.acme.offirent.resource.SaveResourceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.acme.offirent.domain.service.ResourceService;
import com.acme.offirent.resource.ResourceResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ResourcesController{

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all resources",description = "Get all resources",tags = {"resources"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all resources",content =@Content(mediaType = "application/json") )
                })
    @GetMapping("/resources")
    public List<ResourceResource> getAllResource(Pageable pageable){

        Page<Resource> resourcePage = resourceService.getAllResources(pageable);
        List<ResourceResource> resources = resourcePage.getContent()
        .stream().map(this::convertToResource).collect(Collectors.toList());

       // return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }

    @Operation(summary = "Get resource by resourceId",description = "Get resources by a given resourceId",tags = {"resources"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get resources with by a given resourceId",content =@Content(mediaType = "application/json") )
                })
    @GetMapping("/resources/{id}")
    public ResourceResource getResourceById(@PathVariable(name = "id") Long resourceId){
        return convertToResource(resourceService.getResourceById(resourceId)); 
    }




    private Resource convertToEntity(SaveResourceResource resource){return  mapper.map(resource, Resource.class);}

    private ResourceResource convertToResource(Resource entity){return  mapper.map(entity,ResourceResource.class);}
}