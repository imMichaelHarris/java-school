package com.lambdaschool.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @PostMapping(value = "/create", consumes = {"applocatio/json"}, produces = {"appication/json"})
    public ResponseEntity<?> createInstructor(){
        return new ResponseEntity<>();
    }
}
