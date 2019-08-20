package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "Returns all courses in database", response = Course.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Courses retrieved", response = Course.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/courses", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses(
            @PageableDefault(page=0, size = 2)
            Pageable pageable, HttpServletRequest request)
    {
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURL() + "accessed at info level");
        ArrayList<Course> myCourses = courseService.findAll(pageable);
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns course and amount of students currently in that course", response = Course.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Courses and student count retrieved.", response = Course.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses(HttpServletRequest request)
    {
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURL() + "accessed at info level");
        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a course that matches the courseid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Course deleted"),
            @ApiResponse(code = 404, message = "Course not found", response = ErrorDetail.class)
    })
    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(
            @ApiParam(value = "Course Id", required = true, example = "2")
            @PathVariable
                    long courseid, HttpServletRequest request)
    {
        logger.info(request.getMethod().toUpperCase() + " " + request.getRequestURL() + "accessed at info level");
        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
