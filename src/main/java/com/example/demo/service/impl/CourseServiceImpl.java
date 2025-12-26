package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UniversityRepository universityRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UniversityRepository universityRepository) {
        this.courseRepository = courseRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Course createCourse(Long universityId, Course course) {

        if (course.getCreditHours() <= 0) {
            throw new RuntimeException("> 0");
        }

        University uni = universityRepository.findById(universityId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        courseRepository.findByUniversityIdAndCourseCode(universityId, course.getCourseCode())
                .ifPresent(c -> { throw new RuntimeException("exists"); });

        course.setUniversity(uni);
        course.setActive(true);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course updated) {
        Course course = getCourseById(id);
        course.setCourseName(updated.getCourseName());
        course.setCreditHours(updated.getCreditHours());
        course.setDepartment(updated.getDepartment());
        course.setDescription(updated.getDescription());
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return courseRepository.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = getCourseById(id);
        course.setActive(false);
        courseRepository.save(course);
    }
}
