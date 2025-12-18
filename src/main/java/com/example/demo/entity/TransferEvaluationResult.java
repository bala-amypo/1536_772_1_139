package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_evaluation_results")
public class TransferEvaluationResult {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "source_course_id", nullable = false)
private Course sourceCourse;

@ManyToOne(optional = false)
@JoinColumn(name = "target_course_id", nullable = false)
private Course targetCourse;

@Column(nullable = false)
private Double overlapPercentage;

@Column(nullable = false)
private Integer creditHourDifference;

@Column(nullable = false)
private Boolean isEligibleForTransfer;

@Column(nullable = false)
private LocalDateTime evaluatedAt;

@Column(length = 1000)
private String notes;

public TransferEvaluationResult() {
this.evaluatedAt = LocalDateTime.now();
}

public TransferEvaluationResult(Course sourceCourse,
Course targetCourse,
Double overlapPercentage,
Integer creditHourDifference,
Boolean isEligibleForTransfer,
String notes) {
this.sourceCourse = sourceCourse;
this.targetCourse = targetCourse;
this.overlapPercentage = overlapPercentage;
this.creditHourDifference = creditHourDifference;
this.isEligibleForTransfer = isEligibleForTransfer;
this.notes = notes;
this.evaluatedAt = LocalDateTime.now();
}

public Long getId() {
return id;
}

public Course getSourceCourse() {
return sourceCourse;
}

public void setSourceCourse(Course sourceCourse) {
this.sourceCourse = sourceCourse;
}

public Course getTargetCourse() {
return targetCourse;
}

public void setTargetCourse(Course targetCourse) {
this.targetCourse = targetCourse;
}

public Double getOverlapPercentage() {
return overlapPercentage;
}

public void setOverlapPercentage(Double overlapPercentage) {
this.overlapPercentage = overlapPercentage;
}

public Integer getCreditHourDifference() {
return creditHourDifference;
}

public void setCreditHourDifference(Integer creditHourDifference) {
this.creditHourDifference = creditHourDifference;
}

public Boolean getIsEligibleForTransfer() {
return isEligibleForTransfer;
}

public void setIsEligibleForTransfer(Boolean isEligibleForTransfer) {
this.isEligibleForTransfer = isEligibleForTransfer;
}

public LocalDateTime getEvaluatedAt() {
return evaluatedAt;
}

public void setEvaluatedAt(LocalDateTime evaluatedAt) {
this.evaluatedAt = evaluatedAt;
}

public String getNotes() {
return notes;
}

public void setNotes(String notes) {
this.notes = notes;
}
}