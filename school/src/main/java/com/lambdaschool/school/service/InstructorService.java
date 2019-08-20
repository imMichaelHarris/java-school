package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Instructor;

import java.util.ArrayList;

public interface InstructorService
{
    ArrayList<Instructor> findAll();

    Instructor create (Instructor instructor);
}
