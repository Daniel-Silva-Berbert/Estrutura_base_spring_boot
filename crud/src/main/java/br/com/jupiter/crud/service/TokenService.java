package br.com.jupiter.crud.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
  public final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  public String generateToken(String userName) {
    return JWT.create()
      .withSubject(userName)
      .withExpiresAt(generateExpiration())
      .sign(algorithm);
  }

  public Instant generateExpiration() {
    return Instant.now().plus(2, ChronoUnit.HOURS);
  }

  public String validateToken(String token) {
    return JWT.require(algorithm)
      .build()
      .verify(token)
      .getSubject();
  }
}
