package com.codeurinfo.easytransapi.security.model;

import java.util.Objects;

public class AuthenticationResponse {

  private String jwt;

  public AuthenticationResponse() {}

  public AuthenticationResponse(String jwt) {
    this.jwt = jwt;
  }
}

  // getters and setters
