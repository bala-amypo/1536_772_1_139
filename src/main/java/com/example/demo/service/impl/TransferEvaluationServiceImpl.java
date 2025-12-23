package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final TransferEvaluationResultRepository transferEvaluationResultRepo;
    private final CourseRepository courseRepo;
    private final CourseContentTopicRepository topicRepo;
    private final TransferRuleRepository ruleRepo;

    public TransferEvaluationServiceImpl(
            TransferEvaluationResultRepository transferEvaluationResultRepo,
            CourseRepository courseRepo,
            CourseContentTopicRepository topicRepo,
            TransferRuleRepository ruleRepo) {
        this.transferEvaluationResultRepo = transferEvaluationResultRepo;
        this.courseRepo = courseRepo;
        this.topicRepo = topicRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepo.findById(sourceCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        Course target = courseRepo.findById(targetCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        List<TransferRule> rules = ruleRepo
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId()
                );

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);
        result.setEvaluatedAt(new Timestamp(System.currentTimeMillis()));

        if (rules.isEmpty()) {
            result.setIsEligibleForTransfer(false);
            result.setNotes("No active transfer rule");
            return transferEvaluationResultRepo.save(result);
        }

        double overlap = 0;
        for (CourseContentTopic s : topicRepo.findByCourseId(sourceCourseId)) {
            for (CourseContentTopic t : topicRepo.findByCourseId(targetCourseId)) {
                if (s.getTopicName().equalsIgnoreCase(t.getTopicName())) {
                    overlap += Math.min(s.getWeightPercentage(), t.getWeightPercentage());
                }
            }
        }

        TransferRule rule = rules.get(0);
        int creditDiff = Math.abs(source.getCreditHours() - target.getCreditHours());

        boolean eligible =
                overlap >= rule.getMinimumOverlapPercentage()
                        && creditDiff <= rule.getCreditHourTolerance();

        result.setOverlapPercentage(overlap);
        result.setCreditHourDifference(creditDiff);
        result.setIsEligibleForTransfer(eligible);
        result.setNotes(eligible ? "Eligible" : "No active rule satisfied");

        return transferEvaluationResultRepo.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return transferEvaluationResultRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return transferEvaluationResultRepo.findBySourceCourseId(courseId);
    }
}
