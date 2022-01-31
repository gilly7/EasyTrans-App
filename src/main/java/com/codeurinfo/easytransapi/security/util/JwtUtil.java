package com.codeurinfo.easytransapi.security.util;

import com.codeurinfo.easytransapi.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

  // Give a secret key for JWT token generating & validation
  private String SECRET_KEY = "secret";

  // get username from the provided token
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  //get the token expiration time
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  // get token infos
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(UserDetailsImpl userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  /**
   * Generate a token for the provided subject (username)
   * @param claims
   * @param subject
   */
  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts
      .builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //Expiration in 36,000,000 ms (10 hours)
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // signing with HS256 algorythm
      .compact();//According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
      //   compaction of the JWT to a URL-safe string 
  }

  /**
   * Validate token
   */
  public Boolean validateToken(String token, UserDetailsImpl userDetails) {
    final String username = extractUsername(token);

    return (
      username.equals(userDetails.getUsername()) && !isTokenExpired(token)
    );
  }
}
