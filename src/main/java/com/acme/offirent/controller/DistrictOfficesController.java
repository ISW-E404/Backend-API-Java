package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.resource.OfficeResource;
import com.acme.offirent.resource.SaveOfficeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DistrictOfficesController {


    @Autowired
    private OfficeService officeService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all offices by districtId",description = "Get all Offices by given Disctrict Id",tags = {"districts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Offices by given district Id",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/districts/{districtId}/offices")
    public List<OfficeResource> getAllOfficesByDistrictId(@PathVariable(name="districtId") Long districtId, Pageable pageable){
        Page<Office> resourcePage = officeService.getAllOfficesByDistrictId(districtId, pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable, resources.size());
        return resources;
    }


    private Office convertToEntity(SaveOfficeResource resource){
        return mapper.map(resource, Office.class);
    }

    private OfficeResource convertToResource(Office entity){
        return mapper.map(entity, OfficeResource.class);
    }
}
