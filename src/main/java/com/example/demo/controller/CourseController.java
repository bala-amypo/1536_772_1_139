package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/university/{universityId}")
    public Course create(@PathVariable Long universityId,
                         @RequestBody Course course) {
        return courseService.createCourse(universityId, course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id,
                         @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/university/{universityId}")
    public List<Course> getByUniversity(@PathVariable Long universityId) {
        return courseService.getCoursesByUniversity(universityId);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        courseService.deactivateCourse(id);
    }
}
