package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.resource.AccountResource;
import com.acme.offirent.resource.OfficeResource;
import com.acme.offirent.resource.SaveAccountResource;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OfficesController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all offices",description = "Get all offices",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all offices",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices")
    public List<OfficeResource> getAllOffices(Pageable pageable){

        Page<Office> resourcePage = officeService.getAllOffices(pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }

    @Operation(summary = "Get all offices by account of OffiProvider(owner)",description = "Get all Offices by given Account Id",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Offices by given Account Id",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/accounts/{accountId}/offices")
    public List<OfficeResource> getAllOfficesByAccountId(@PathVariable(name = "accountId") Long accountId,Pageable pageable){

        Page<Office> resourcePage = officeService.getAllOfficesByAccountId(accountId,pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }


    @Operation(summary = "Get all offices by account of OffiProvider(owner)",description = "Get all Offices by given Account Email",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Offices by given Account Id",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/accounts/email/{accountEmail}/offices")
    public List<OfficeResource> getAllOfficesByAccountEmail(@PathVariable(name = "accountEmail") String accountEmail,Pageable pageable) {

        Page<Office> resourcePage = officeService.getAllOfficesByAccountEmail(accountEmail, pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }



    @Operation(summary = "Get Office by Id", description = "Get Office for given Id", tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Office returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/offices/{officeId}")
    public OfficeResource getOfficeById(@PathVariable(name = "officeId") Long officeId){
        return convertToResource(officeService.getOfficeById(officeId));
    }

    @Operation(summary = "Create Office with accountId of OffiProvider(owner)",description = "Enter a new Office at register",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enter a new office for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts/{accountId}/District={districtId}/offices")
    public OfficeResource createOfficeWithAccountId(@PathVariable(name = "accountId") Long accountId, @PathVariable(name = "districtId") Long districtId, @Valid @RequestBody SaveOfficeResource resource){
        return convertToResource(
                officeService.createOffice(convertToEntity(resource),accountId,districtId));
    }


    @Operation(summary = "Create Office with accountEmail of OffiProvider(owner)",description = "Enter a new Office at register",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enter a new office for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts/email/{accountEmail}/District={districtId}/offices")
    public OfficeResource createOfficeWithAccountEmail(@PathVariable(name = "accountEmail") String accountEmail, @PathVariable(name = "districtId") Long districtId, @Valid @RequestBody SaveOfficeResource resource){
        return convertToResource(
                officeService.createOfficeWithAccountEmail(convertToEntity(resource),accountEmail,districtId));
    }


    @Operation(summary = "Get all the offices that have less or the same price",description = "Get all offices by given price",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Offices by given price",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices/price/{price}")
    public List<OfficeResource> getAllOfficesByPriceLessThanEqual(@PathVariable(name = "price") Float price, Pageable pageable){
        Page<Office> resourcePage = officeService.getAllOfficesByPriceLessThanEqual(price, pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable, resources.size());
        return resources;
    }

    @Operation(summary = "Get all the offices that have price between two given prices",description = "Get all offices by given two prices",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all offices by given two prices",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices/minPrice/{minPrice}/maxPrice/{maxPrice}")
    public List<OfficeResource> getAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(@PathVariable(name = "minPrice") Float minPrice,@PathVariable(name = "maxPrice") Float maxPrice,  Pageable pageable){
        Page<Office> resourcePage = officeService.getAllOfficesByPriceLessThanEqualAndPriceGreaterThanEqual(minPrice, maxPrice, pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable, resources.size());
        return resources;
    }



    @Operation(summary = "Get the 5 offices with highest score",description = "Get the 5 offices with highest score",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Offices by higher score",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices/score/fiveHighest")
    public List<OfficeResource> getAllOfficesByPriceLessThanEqual(Pageable pageable){
        Page<Office> resourcePage = officeService.getOfficesByScoreDescFirst5(pageable);
        List<OfficeResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable, resources.size());
        return resources;
    }





    @Operation(summary = "Update Offices",description = "Update Office for given Id",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update information of office for given Id",content =@Content(mediaType = "application/json") )
    })
    @PutMapping("/offices/{id}")
    public OfficeResource updateOffice(@PathVariable(name = "id")   Long officeId,@Valid @RequestBody SaveOfficeResource resource){
        return convertToResource(officeService.updateOffice(officeId,convertToEntity(resource)));
    }

    @Operation(summary = "Active Offices",description = "Active a deactivated Office",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Change the office's status to activated",content =@Content(mediaType = "application/json") )
    })
    @PutMapping("/offices/{accountId}/{id}")
    public  OfficeResource activeOffice(@PathVariable(name = "accountId")Long accountId,@PathVariable(name = "id") Long officeId){
        return  convertToResource(officeService.activeOffice(accountId,officeId));
    }

    @Operation(summary = "Delete Offices",description = "Delete Office for given Id",tags = {"offices"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete office for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/offices/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable(name="id") Long officeId){
        return officeService.deleteOffice(officeId);
    }

    private Office convertToEntity(SaveOfficeResource resource){return  mapper.map(resource, Office.class);}

    private OfficeResource convertToResource(Office entity){return  mapper.map(entity,OfficeResource.class);}

    private Office convertToUpdateResource(OfficeResource resource){return  mapper.map(resource,Office.class);}
}