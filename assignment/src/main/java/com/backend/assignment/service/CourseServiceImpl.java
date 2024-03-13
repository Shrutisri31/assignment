package com.backend.assignment.service;

import com.backend.assignment.entity.Course;
import com.backend.assignment.exception.CourseNotFoundException;
import com.backend.assignment.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseServiceImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepo.findById(courseId);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Optional<Course> existingCourse = courseRepo.findById(courseId);
        if (existingCourse.isPresent()) {
            // Update the existing course with new details
            Course updatedCourse = existingCourse.get();
            updatedCourse.setName(course.getName());
            updatedCourse.setSubject(course.getSubject());
            updatedCourse.setChapters(course.getChapters());
            updatedCourse.setNoOfClasses(course.getNoOfClasses());
            updatedCourse.setCourseType(course.getCourseType());
            updatedCourse.setLearnMode(course.getLearnMode());
            return courseRepo.save(updatedCourse);
        } else {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found");
        }
    }

    @Override
    public boolean deleteCourse(Long courseId) {
        Optional<Course> existingCourse = courseRepo.findById(courseId);
        if (existingCourse.isPresent()) {
            courseRepo.deleteById(courseId);
            return true;
        } else {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found");
        }
    }
}
