package com.example.demo.controller;

import com.example.demo.entity.TransferEvaluationResult;
import com.example.demo.service.TransferEvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer-evaluation")
public class TransferEvaluationController {

    private final TransferEvaluationService evaluationService;

    public TransferEvaluationController(TransferEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public TransferEvaluationResult evaluate(@RequestParam Long sourceCourseId,
                                             @RequestParam Long targetCourseId) {
        return evaluationService.evaluateTransfer(sourceCourseId, targetCourseId);
    }

    @GetMapping("/{id}")
    public TransferEvaluationResult getById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<TransferEvaluationResult> getByCourse(@PathVariable Long courseId) {
        return evaluationService.getEvaluationsForCourse(courseId);
    }
}
