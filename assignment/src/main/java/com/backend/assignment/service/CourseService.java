package com.backend.assignment.service;

import com.backend.assignment.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long courseId);

    Course createCourse(Course course);

    Course updateCourse(Long courseId, Course course);

    boolean deleteCourse(Long courseId);
}
