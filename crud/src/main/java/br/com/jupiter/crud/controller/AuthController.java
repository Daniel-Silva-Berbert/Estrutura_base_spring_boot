package br.com.jupiter.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jupiter.crud.controller.dto.AuthDto;
import br.com.jupiter.crud.controller.dto.RegisterDTO;
import br.com.jupiter.crud.controller.dto.TokenDto;
import br.com.jupiter.crud.entity.Usuario;
import br.com.jupiter.crud.repository.UsuarioRepository;
import br.com.jupiter.crud.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

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

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
    
    Optional<Usuario> existingUser = this.usuarioRepository.findByUserName(data.userName());

    if (existingUser.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("UserName já existe!"); // Retorna um erro 409
    }

    
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    Usuario newUser = new Usuario(data.userName(), data.email(), encryptedPassword, data.permissao());

    
    this.usuarioRepository.save(newUser);
    String token = tokenService.generateToken(newUser.getUserName());

    return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDto(token));
}

}
