package com.codeurinfo.easytransapi.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String name;

  private String start;
  private String terminus;

public Route() {}

  public Route(String name, String start, String terminus) {
    this.name = name;
    this.start = start;
    this.terminus = terminus;
  }

/**
Getters and Setters
*/

}
