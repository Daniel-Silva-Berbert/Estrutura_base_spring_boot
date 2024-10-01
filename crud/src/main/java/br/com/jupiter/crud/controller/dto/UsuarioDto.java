package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Usuario;

import java.time.LocalDate;
import java.util.List;

public record UsuarioDto(
  Long id,
  String nome,
  String userName,
  String email,
  LocalDate nascimento,
  CargoDto cargo,
  List<ProjetoDto> projetos,
  List<PermissaoDto> permissoes
) {

  public static UsuarioDto fromEntity(Usuario usuario) {
    CargoDto cargoDto = usuario.getCargo() != null ?
      CargoDto.fromEntity(usuario.getCargo()) : null;

    return new UsuarioDto(
      usuario.getId(),
      usuario.getNome(),
      usuario.getUsername(),
      usuario.getEmail(),
      usuario.getNascimento(),
      cargoDto,
      usuario.getProjetos().stream()
        .map(ProjetoDto::fromEntity).toList(),
      usuario.getPermissoes().stream()
        .map(PermissaoDto::fromEntity).toList()
    );
  }
}
