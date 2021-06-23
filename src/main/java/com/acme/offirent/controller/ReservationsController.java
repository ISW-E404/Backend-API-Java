package com.acme.offirent.controller;


import com.acme.offirent.domain.model.Office;
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


    @Operation(summary = "Get all Reservations",description = "Get all reservations",tags = {"reservations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all reservations",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/reservations")
    public List<ReservationResource> getAllReservations(Pageable pageable){

        Page<Reservation> resourcePage = reservationService.getAllReservations(pageable);
        List<ReservationResource> resources = resourcePage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }


    @Operation(summary = "Get Reservation by Id", description = "Get Reservation for given Id", tags = {"reservations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reservations/{reservationId}")
    public ReservationResource getReservationById(@PathVariable(name = "reservationId") Long reservationId){
        return convertToResource(reservationService.getReservationById(reservationId));
    }


    @Operation(summary = "Get all reservations by Account of OffiUser(client)",description = "Get all reservations by given AccountId for OffiUser(client)",tags = {"accounts"})
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

    @Operation(summary = "Get all reservations by AccountEmail of OffiUser(client)",description = "Get all reservations by given AccountEmail for OffiUser(client)",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all reservations by given AccountEmail",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/accounts/{accountEmail}/reservations")
    public List<ReservationResource> getAllReservationsByAccountEmail(
            @PathVariable(name = "accountEmail") String accountEmail, Pageable pageable){

        Page<Reservation> reservationPage = reservationService.getAllReservationsByAccountEmail(accountEmail,pageable);
        List<ReservationResource> resources = reservationPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        //return new PageImpl<>(resources,pageable,resources.size());'
        return resources;
    }



    @Operation(summary = "Get all reservations by Office",description = "Get all reservations by given OfficeId",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all reservations by given OfficeId",content =@Content(mediaType = "application/json") )
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

    @Operation(summary = "Create Reservation with accountId of OffiUser(client)",description = "Create a new Reservation with accountId of OffiUser(client)",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new Reservation for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts/{accountId}/Office={officeId}/reservations")
    public ReservationResource createReservationWithAccountId(@PathVariable(name = "accountId") Long accountId,@PathVariable(name = "officeId") Long officeId, @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(
                reservationService.createReservationWithAccountId(convertToEntity(resource),accountId,officeId));
    }


    @Operation(summary = "Create Reservation with accountEmail of OffiUser(client)",description = "Create a new Reservation with accountEmail of OffiUser(client)",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new Reservation for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts/{accountEmail}/Office={officeId}/reservations")
    public ReservationResource createReservationWithAccountEmail(@PathVariable(name = "accountEmail") String  accountEmail,@PathVariable(name = "officeId") Long officeId, @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(
                reservationService.createReservationWithAccountEmail(convertToEntity(resource),accountEmail,officeId));
    }


    @Operation(summary = "Update Reservation",description = "Update Reservation for given Id",tags = {"reservations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update information of reservation for given Id",content =@Content(mediaType = "application/json") )
    })
    @PutMapping("/reservation/{id}")
    public ReservationResource updateReservation(@PathVariable(name = "id")   Long reservationId,@Valid @RequestBody SaveReservationResource resource){
        return convertToResource(reservationService.updateReservation(reservationId,convertToEntity(resource)));
    }



    @Operation(summary = "Delete Reservation for given Id of OffiUser(client)",description = "Delete Reservation for given Id of OffiUser(client)",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Reservation for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/accounts/{accountId}/reservations/{reservationId}")
    public ResponseEntity<?> deleteDistrict(
            @PathVariable(name = "accountId") Long accountId,
            @PathVariable(name = "reservationId") Long reservationId){
        return reservationService.deleteReservationWithAccountId(accountId,reservationId);
    }


    @Operation(summary = "Delete Reservations",description = "Delete Reservations for given Id",tags = {"reservations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete reservations for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable(name="id") Long reservationId){
        return reservationService.deleteReservation(reservationId);
    }



    private Reservation convertToEntity(SaveReservationResource resource){
        return mapper.map(resource,Reservation.class);
    }
    private ReservationResource convertToResource(Reservation entity){
        return mapper.map(entity,ReservationResource.class);
    }
}
