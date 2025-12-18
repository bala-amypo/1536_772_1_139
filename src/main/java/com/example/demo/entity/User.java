package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
name = "users",
uniqueConstraints = {
@UniqueConstraint(columnNames = "email")
}
)
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String email;

@Column(nullable = false)
private String password; // store hashed password

@ElementCollection(fetch = FetchType.EAGER)
@CollectionTable(
name = "user_roles",
joinColumns = @JoinColumn(name = "user_id")
)
@Column(name = "role")
private Set<String> roles = new HashSet<>();

@Column(nullable = false, updatable = false)
private LocalDateTime createdAt;

public User() {}

public User(String email, String password, Set<String> roles) {
this.email = email;
this.password = password;
this.roles = roles;
this.createdAt = LocalDateTime.now();
}

@PrePersist
protected void onCreate() {
this.createdAt = LocalDateTime.now();
}

public Long getId() {
return id;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public Set<String> getRoles() {
return roles;
}

public void setRoles(Set<String> roles) {
this.roles = roles;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}
}