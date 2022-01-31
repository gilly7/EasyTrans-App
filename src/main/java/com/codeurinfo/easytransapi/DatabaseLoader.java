package com.codeurinfo.easytransapi;

import com.codeurinfo.easytransapi.security.model.ERole;
import com.codeurinfo.easytransapi.security.model.User;
import com.codeurinfo.easytransapi.security.repository.UserRepository;
import java.util.Arrays;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Override
  public void run(String... args) throws Exception {
    // remember we use PasswordEncoder to configure the AuthenticationManagerBuilder in the SecurityConfig class
    // thus you must encode  pwd using the same encoder (BCryptPasswordEncoder) before persit
    String pwd1 = encoder.encode("will");
    String pwd2 = encoder.encode("user");
    userRepository.save(
      new User(
        "will",
        pwd1,
        "Wilson",
        Set.copyOf(Arrays.asList(ERole.ROLE_ADMIN, ERole.ROLE_USER))
      )
    );
    userRepository.save(
      new User(
        "user",
        pwd2,
        "Adjowa",
        Set.copyOf(Arrays.asList(ERole.ROLE_USER))
      )
    );
  }
}
