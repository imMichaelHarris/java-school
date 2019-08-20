package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @ApiOperation(value = "Returns list of all instructors with their courses", response = Instructor.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instructors retrieved", response = Instructor.class),
            @ApiResponse(code = 404, message = "Could not retrieve instructors", response = ErrorDetail.class)
    })
    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<?> getInstructors(){
        ArrayList<Instructor> instructorList = instructorService.findAll();
        return new ResponseEntity<>(instructorList, HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new instructor", notes = "The newly created instructor id will be sent in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Instructor created", response = void.class),
            @ApiResponse(code = 500, message = "Error creating instructor", response = ErrorDetail.class)
    })
    @PostMapping(value = "/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createInstructor(@Valid @RequestBody Instructor newInstructor){
        newInstructor = instructorService.create(newInstructor);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newInstructorURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{instructorid}").buildAndExpand(newInstructor.getInstructid()).toUri();
        responseHeaders.setLocation(newInstructorURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}