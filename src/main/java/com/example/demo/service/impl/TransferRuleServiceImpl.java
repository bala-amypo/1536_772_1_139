package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository transferRuleRepository;

    public TransferRuleServiceImpl(TransferRuleRepository transferRuleRepository) {
        this.transferRuleRepository = transferRuleRepository;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        rule.setActive(true);
        return transferRuleRepository.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = transferRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));

        
        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        existing.setActive(rule.getActive());

        return transferRuleRepository.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return transferRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return transferRuleRepository
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = transferRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));

        rule.setActive(false);
        transferRuleRepository.save(rule);
    }
}
