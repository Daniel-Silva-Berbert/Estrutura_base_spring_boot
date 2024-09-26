package br.com.jupiter.crud.controller.dto;

import br.com.jupiter.crud.entity.Projeto;
import br.com.jupiter.crud.entity.Usuario;

import java.util.List;

public record ProjetoDto(Long id, String nome) {

  public static ProjetoDto fromEntity(Projeto projeto) {
    return new ProjetoDto(
      projeto.getId(),
      projeto.getNome()
    );
  }
}
