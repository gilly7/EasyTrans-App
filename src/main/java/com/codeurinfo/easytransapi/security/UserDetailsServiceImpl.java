package com.codeurinfo.easytransapi.security;

import com.codeurinfo.easytransapi.security.model.User;
import com.codeurinfo.easytransapi.security.repository.UserRepository;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Override this method to fetch user by userName from th db
   * using the UserRepository
   * @Transactional is used to manage User & roles collection lazy loading
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String userName) {

    User user = userRepository.findByUserName(userName).orElse(null);

    return UserDetailsImpl.build(user);
  }
}
