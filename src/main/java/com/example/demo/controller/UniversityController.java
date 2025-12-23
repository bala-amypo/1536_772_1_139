package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping
    public University create(@RequestBody University university) {
        return universityService.createUniversity(university);
    }

    @PutMapping("/{id}")
    public University update(@PathVariable Long id,
                             @RequestBody University university) {
        return universityService.updateUniversity(id, university);
    }

    @GetMapping("/{id}")
    public University getById(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    @GetMapping
    public List<University> getAll() {
        return universityService.getAllUniversities();
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        universityService.deactivateUniversity(id);
    }
}
