package com.codeurinfo.easytransapi;

import com.codeurinfo.easytransapi.security.UserDetailsImpl;
import com.codeurinfo.easytransapi.security.UserDetailsServiceImpl;
import com.codeurinfo.easytransapi.security.model.AuthenticationRequest;
import com.codeurinfo.easytransapi.security.model.AuthenticationResponse;
import com.codeurinfo.easytransapi.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/api/auth/login")
  public ResponseEntity createAuthenticationToken(
    @RequestBody AuthenticationRequest request
  )
    throws Exception {
    Authentication authentication = null;
    try {
      // Use AuthenticationManager to verify the provided username/password
      authentication =
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
            request.getUserName(),
            request.getPassword()
          )
        );
    } catch (Exception exception) {
      throw new Exception("Incorrect username or password");
    }
    //if username/password valid, put the Authentication created object in the SecurityContextHolder's context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Retrieve the user from the UserDetailsServiceImpl
    final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(
      request.getUserName()
    );

    // Generate the token from the JWT util class
    String jwt = jwtUtil.generateToken(userDetails);

    // Send AuthenticationResponse response to client
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
