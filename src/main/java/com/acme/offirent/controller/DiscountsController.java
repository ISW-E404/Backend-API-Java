package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Discount;
import com.acme.offirent.domain.service.DiscountService;
import com.acme.offirent.resource.DiscountResource;
import com.acme.offirent.resource.SaveDiscountResource;
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
public class DiscountsController{

    @Autowired
    private DiscountService discountService;

    @Autowired
    private ModelMapper mapper;


    @Operation(summary = "Get Discounts", description = "Get All Discounts by Pages", tags = {"discounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Discounts returned", content = @Content(mediaType = "application/json"))
                })
    @GetMapping("/discounts")
    public List<DiscountResource> getAllDiscounts(Pageable pageable){

       List<DiscountResource> discounts= discountService.getAllDiscounts(pageable)
               .getContent().stream().map(this::convertToResource)
               .collect(Collectors.toList());

       int discountsCount= discounts.size();
       //return new PageImpl<>(discounts,pageable,discountsCount);
        return discounts;
    }

    @Operation(summary = "Get discount by discountId",description = "Get discounts by a given discountId",tags = {"discounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get discounts with by a given discountId",content =@Content(mediaType = "application/json") )
                })
    @GetMapping("/discounts/{id}")
    public DiscountResource getDiscountById(@PathVariable(name = "id") Long discountId){
        return convertToResource(discountService.getDiscountById(discountId));
    }

    @Operation(summary = "Create Discounts ",description = "Create a new Discount",tags = {"discounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new Discount for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/discounts")
    public DiscountResource createDiscount(@Valid @RequestBody SaveDiscountResource resource){
        return convertToResource(
                discountService.createDiscount(convertToEntity(resource)));
    }


    @Operation(summary = "Update Discounts",description = "Update Discount for given Id",tags = {"discounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update information of discount for given Id",content =@Content(mediaType = "application/json") )
    })
    @PutMapping("/discounts/{id}")
    public DiscountResource updateDiscount(@PathVariable(name = "id")   Long discountId,@Valid @RequestBody SaveDiscountResource resource){
        return convertToResource(discountService.updateDiscount(discountId,convertToEntity(resource)));
    }

    @Operation(summary = "Delete Discounts",description = "Delete Discount for given Id",tags = {"discounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete discount for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/discounts/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable(name="id") Long discountId){
        return discountService.deleteDiscount(discountId);
    }


    private Discount convertToEntity(SaveDiscountResource resource){
        return mapper.map(resource, Discount.class);
    }

    private DiscountResource convertToResource(Discount entity){
        return mapper.map(entity, DiscountResource.class);
    }
}
