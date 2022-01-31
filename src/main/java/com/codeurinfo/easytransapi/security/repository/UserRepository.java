package com.codeurinfo.easytransapi.security.repository;

import com.codeurinfo.easytransapi.security.model.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String userName);
}
