package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;
    @PostMapping(value = "/create", consumes = {"applocatio/json"}, produces = {"appication/json"})
    public ResponseEntity<?> createInstructor(@Valid @RequestBody Instructor newInstructor){
        newInstructor = instructorService.create(newInstructor);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newInstructorURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{instructorid}").buildAndExpand(newInstructor.getInstructid()).toUri();
        responseHeaders.setLocation(newInstructorURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
