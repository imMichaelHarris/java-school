package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.view.GetInstructors;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface InstructorRepository extends CrudRepository<Instructor, Long>
{

    @Query(value = "SELECT * FROM instructor", nativeQuery = true)
    ArrayList<GetInstructors> getInstructors(String name);
}
