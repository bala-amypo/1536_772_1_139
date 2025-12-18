package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_content_topics")
public class CourseContentTopic {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "course_id", nullable = false)
private Course course;

@Column(nullable = false)
private String topicName;

@Column(nullable = false)
private Double weightPercentage;

public CourseContentTopic() {}

public CourseContentTopic(Course course, String topicName, Double weightPercentage) {
this.course = course;
setTopicName(topicName);
setWeightPercentage(weightPercentage);
}

public Long getId() {
return id;
}

public Course getCourse() {
return course;
}

public void setCourse(Course course) {
this.course = course;
}

public String getTopicName() {
return topicName;
}

public void setTopicName(String topicName) {
if (topicName == null || topicName.trim().isEmpty()) {
throw new RuntimeException("Topic name cannot be empty");
}
this.topicName = topicName;
}

public Double getWeightPercentage() {
return weightPercentage;
}

public void setWeightPercentage(Double weightPercentage) {
if (weightPercentage == null || weightPercentage < 0 || weightPercentage > 100) {
throw new RuntimeException("weightPercentage must be 0-100");
}
this.weightPercentage = weightPercentage;
}
}