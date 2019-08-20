package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public ArrayList<Instructor> findAll() {
        ArrayList<Instructor> list = new ArrayList<>();
        instructrepos.findAll().iterator().forEachRemaining(list::add);
        return  list;
    }

    @Override
    public Instructor create(Instructor instructor) {
        return instructrepos.save(instructor);
    }
}
