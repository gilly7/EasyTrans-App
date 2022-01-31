package com.codeurinfo.easytransapi.security;

import com.codeurinfo.easytransapi.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class UserDetailsImpl implements UserDetails {

  private Long id;

  private String username;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(
    Long id,
    String username,
    String password,
    Collection<? extends GrantedAuthority> authorities
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  /**
   * Static method to expose UserDetailsImpl object created by given user
   * @param user
   * @return UserDetailsImpl object
   */
  public static UserDetailsImpl build(User user) {
    // Map user roles list to Spring Security GrantedAuthority list
    List<GrantedAuthority> authorities = List
      .copyOf(user.getRoles())
      .stream()
      .map(role -> new SimpleGrantedAuthority(role.name()))
      .collect(Collectors.toList());

    return new UserDetailsImpl(
      user.getId(),
      user.getUserName(),
      user.getPassword(),
      authorities
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
