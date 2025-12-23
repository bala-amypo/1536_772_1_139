package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer-rules")
public class TransferRuleController {

    private final TransferRuleService transferRuleService;

    public TransferRuleController(TransferRuleService transferRuleService) {
        this.transferRuleService = transferRuleService;
    }

    @PostMapping
    public TransferRule create(@RequestBody TransferRule rule) {
        return transferRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public TransferRule update(@PathVariable Long id,
                               @RequestBody TransferRule rule) {
        return transferRuleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public TransferRule getById(@PathVariable Long id) {
        return transferRuleService.getRuleById(id);
    }

    @GetMapping("/source/{sourceId}/target/{targetId}")
    public List<TransferRule> getRules(@PathVariable Long sourceId,
                                       @PathVariable Long targetId) {
        return transferRuleService.getRulesForUniversities(sourceId, targetId);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        transferRuleService.deactivateRule(id);
    }
}
