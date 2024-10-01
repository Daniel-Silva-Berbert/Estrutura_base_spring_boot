package br.com.jupiter.crud.controller;

import br.com.jupiter.crud.controller.dto.AuthDto;
import br.com.jupiter.crud.controller.dto.TokenDto;
import br.com.jupiter.crud.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken userNamePassword =
      new UsernamePasswordAuthenticationToken(authDto.userName(), authDto.password());

    Authentication auth = authenticationManager.authenticate(userNamePassword);

    String token = tokenService.generateToken(auth.getName());

    return new TokenDto(token);
  }
}
