package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    // ⚠️ FIELD NAME MUST MATCH
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUniversity(University university) {
        universityRepository.findByName(university.getName())
                .ifPresent(u -> { throw new RuntimeException("exists"); });

        university.setActive(true);
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(Long id, University updated) {
        University uni = getUniversityById(id);
        uni.setName(updated.getName());
        uni.setAccreditationLevel(updated.getAccreditationLevel());
        uni.setCountry(updated.getCountry());
        return universityRepository.save(uni);
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University uni = getUniversityById(id);
        uni.setActive(false);
        universityRepository.save(uni);
    }
}
