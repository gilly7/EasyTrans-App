package com.codeurinfo.easytransapi.security.model;


import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String userName;
  private String password;
  private String name;
  @ElementCollection (targetClass = ERole.class)
  @Enumerated(EnumType.STRING)
  private Set<ERole> roles;

  public User() {}

  public User(
    String userName,
    String password,
    String name,
    Set<ERole> roles
  ) {
    this.userName = userName;
    this.password = password;
    this.name = name;
    this.roles = roles;
  }

public Set<ERole> getRoles() {
	return roles;
}

public void setRoles(Set<ERole> roles) {
	this.roles = roles;
}
/**
   * getters and setters
   */

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


}