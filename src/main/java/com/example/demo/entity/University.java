package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
name = "universities",
uniqueConstraints = {
@UniqueConstraint(columnNames = "name")
}
)
public class University {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String name;

@Column(nullable = false)
private String accreditationLevel;

@Column(nullable = false)
private String country;

@Column(nullable = false)
private Boolean active = true; // default true

// Constructors
public University() {}

public University(String name, String accreditationLevel, String country) {
this.name = name;
this.accreditationLevel = accreditationLevel;
this.country = country;
this.active = true;
}

// Getters & Setters
public Long getId() {
return id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAccreditationLevel() {
return accreditationLevel;
}

public void setAccreditationLevel(String accreditationLevel) {
this.accreditationLevel = accreditationLevel;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public Boolean getActive() {
return active;
}

public void setActive(Boolean active) {
this.active = active;
}
}