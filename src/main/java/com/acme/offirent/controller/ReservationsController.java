package com.acme.offirent.controller;


import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.resource.*;
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
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all reservations by Account",description = "Get all reservations by given AccountId",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all reservations by given AccountId",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/accounts/{accountId}/reservations")
    public List<ReservationResource> getAllReservationsByAccountId(
            @PathVariable(name = "accountId") Long accountId, Pageable pageable){

        Page<Reservation> reservationPage = reservationService.getAllReservationsByAccountId(accountId,pageable);
        List<ReservationResource> resources = reservationPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable,resources.size());'
        return resources;
    }

    @Operation(summary = "Get all reservations by Account",description = "Get all reservations by given AccountId",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all reservations by given AccountId",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/offices/{officeId}/reservations")
    public List<ReservationResource> getAllReservationsByOfficeId(
            @PathVariable(name = "officeId") Long officeId, Pageable pageable){

        Page<Reservation> reservationPage = reservationService.getAllReservationsByOfficeId(officeId,pageable);
        List<ReservationResource> resources = reservationPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
       // return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }

    @Operation(summary = "Create Reservation ",description = "Create a new Reservation",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new Reservation for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts/{accountId}/Office={officeId}/reservations")
    public ReservationResource createReservation(@PathVariable(name = "accountId") Long accountId,@PathVariable(name = "officeId") Long officeId, @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(
                reservationService.createReservation(convertToEntity(resource),accountId,officeId));
    }

    @Operation(summary = "Delete Reservation",description = "Delete Reservation for given Id",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Reservation for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/accounts/{accountId}/reservations/{reservationId}")
    public ResponseEntity<?> deleteDistrict(
            @PathVariable(name = "accountId") Long accountId,
            @PathVariable(name = "reservationId") Long reservationId){
        return reservationService.deleteReservation(accountId,reservationId);
    }


    private Reservation convertToEntity(SaveReservationResource resource){
        return mapper.map(resource,Reservation.class);
    }
    private ReservationResource convertToResource(Reservation entity){
        return mapper.map(entity,ReservationResource.class);
    }
}
