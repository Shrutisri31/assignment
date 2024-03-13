package com.backend.assignment.controller;

import com.backend.assignment.entity.Course;
import com.backend.assignment.exception.CourseNotFoundException;
import com.backend.assignment.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ROLE_COURSE_CREATOR')")
    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return ResponseEntity.ok("Course created with ID: " + createdCourse.getCourseId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating course: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_COURSE_CREATOR')")
    @PutMapping("/update/{courseId}")
    public ResponseEntity<String> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(courseId, course);
            if (updatedCourse != null) {
                return ResponseEntity.ok("Course updated successfully");
            } else {
                throw new CourseNotFoundException("Course with ID " + courseId + " not found");
            }
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating course: " + e.getMessage());
        }
    }



    @PreAuthorize("hasRole('ROLE_COURSE_CREATOR')")
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        try {
            boolean deleted = courseService.deleteCourse(courseId);
            if (deleted) {
                return ResponseEntity.ok("Course deleted successfully");
            } else {
                throw new CourseNotFoundException("Course with ID " + courseId + " not found");
            }
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting course: " + e.getMessage());
        }
    }


    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT') or hasRole('ROLE_COURSE_CREATOR')")
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> allCourses = courseService.getAllCourses();
            return ResponseEntity.ok(allCourses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


    @PreAuthorize("hasRole('ROLE_COURSE_CREATOR')")
    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long courseId) {
        try {
            Optional<Course> courseOptional = courseService.getCourseById(courseId);
            if (courseOptional.isPresent()) {
                return ResponseEntity.ok().body(courseOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID " + courseId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching course: " + e.getMessage());
        }
    }



}
