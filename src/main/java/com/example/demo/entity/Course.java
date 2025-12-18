package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
name = "courses",
uniqueConstraints = {
@UniqueConstraint(columnNames = {"university_id", "course_code"})
}
)
public class Course {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(optional = false)
@JoinColumn(name = "university_id", nullable = false)
private University university;

@Column(name = "course_code", nullable = false)
private String courseCode;

@Column(nullable = false)
private String courseName;

@Column(nullable = false)
private Integer creditHours;

@Column(length = 1000)
private String description;

@Column(nullable = false)
private String department;

@Column(nullable = false)
private Boolean active = true; 

public Course() {}

public Course(University university, String courseCode, String courseName,
Integer creditHours, String description, String department) {
this.university = university;
this.courseCode = courseCode;
this.courseName = courseName;
this.creditHours = creditHours;
this.description = description;
this.department = department;
this.active = true;
}

public Long getId() {
return id;
}

public University getUniversity() {
return university;
}

public void setUniversity(University university) {
this.university = university;
}

public String getCourseCode() {
return courseCode;
}

public void setCourseCode(String courseCode) {
this.courseCode = courseCode;
}

public String getCourseName() {
return courseName;
}

public void setCourseName(String courseName) {
this.courseName = courseName;
}

public Integer getCreditHours() {
return creditHours;
}

public void setCreditHours(Integer creditHours) {
if (creditHours == null || creditHours <= 0) {
throw new RuntimeException("creditHours must be > 0");
}
this.creditHours = creditHours;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getDepartment() {
return department;
}

public void setDepartment(String department) {
this.department = department;
}

public Boolean getActive() {
return active;
}

public void setActive(Boolean active) {
this.active = active;
}
}