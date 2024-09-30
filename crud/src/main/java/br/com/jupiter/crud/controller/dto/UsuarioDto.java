package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Usuario;

import java.time.LocalDate;
import java.util.List;

public record UsuarioDto(
  Long id,
  String userName,
  String email,
  CargoDto cargo
  // List<PermissaoDto> permissoes
) {

  public static UsuarioDto fromEntity(Usuario usuario) {
    CargoDto cargoDto = usuario.getCargo() != null ?
      CargoDto.fromEntity(usuario.getCargo()) : null;

    return new UsuarioDto(
      usuario.getId(),
      usuario.getUserName(),
      usuario.getEmail(),
      cargoDto
      // usuario.getPermissoes().stream()
      //   .map(PermissaoDto::fromEntity).toList()
    );
  }
}
