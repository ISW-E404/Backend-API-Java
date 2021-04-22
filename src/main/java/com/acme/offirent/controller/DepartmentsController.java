package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Department;
import com.acme.offirent.domain.model.Resource;
import com.acme.offirent.domain.service.DepartmentService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all departments",description = "Get all Departments",tags = {"departments"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Departments",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/departments")
    public List<DepartmentResource> getAllDepartments(Pageable pageable){

        Page<Department> departmentPage = departmentService.getAllDepartments(pageable);
        List<DepartmentResource> resources = departmentPage.getContent()
                .stream().map(this::convertToResource).collect(Collectors.toList());

        //return new PageImpl<>(resources,pageable,resources.size());
        return resources;
    }

    @Operation(summary = "Get department by departmentId",description = "Get department by a given departmentId",tags = {"departments"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get department by a given departmentId",content =@Content(mediaType = "application/json") )
    })
    @GetMapping("/departments/{id}")
    public DepartmentResource getDepartmentById(@PathVariable(name = "id") Long departmentId){
        return convertToResource(departmentService.getDepartmentById(departmentId));
    }

    @Operation(summary = "Create Department ",description = "Enter a new Department at register",tags = {"departments"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enter a new department for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/departments/")
    public DepartmentResource createDistrict(@Valid @RequestBody SaveDepartmentResource resource){
        return convertToResource(
                departmentService.createDepartment(convertToEntity(resource)));
    }

    private Department convertToEntity(SaveDepartmentResource resource){return  mapper.map(resource, Department.class);}

    private DepartmentResource convertToResource(Department entity){return  mapper.map(entity,DepartmentResource.class);}
}
