package br.com.jupiter.crud.controller;


import br.com.jupiter.crud.controller.dto.UsuarioCreationDto;
import br.com.jupiter.crud.controller.dto.UsuarioDto;
import br.com.jupiter.crud.entity.Usuario;
import br.com.jupiter.crud.service.UsuarioService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<UsuarioDto> getAll() {
    List<Usuario> allUsuarios = usuarioService.getAll();
    return allUsuarios.stream().map(UsuarioDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public UsuarioDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.getById(id)
    );
  }

  @GetMapping("search")
  public List<Usuario> getByName(@RequestParam String usuario) throws NameNotFoundException {
    return usuarioService.getByName(usuario);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDto salvar(@RequestBody UsuarioCreationDto usuarioCreationDto) {
    return UsuarioDto.fromEntity(
      usuarioService.salvar(usuarioCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public UsuarioDto editar(@PathVariable Long id, @RequestBody UsuarioCreationDto usuarioCreationDto)
    throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.editar(id, usuarioCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public UsuarioDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.delete(id)
    );
  }

  @PutMapping("/{usuarioId}/cargo/{cargoId}")
  public UsuarioDto setUsuarioCargo(@PathVariable Long usuarioId, @PathVariable Long cargoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.setUsuarioCargo(usuarioId, cargoId)
    );
  }

  @DeleteMapping("/{usuarioId}/cargo")
  public UsuarioDto removeUsuarioCargo(@PathVariable Long usuarioId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.removeUsuarioCargo(usuarioId)
    );
  }

  @PutMapping("/{usuarioId}/projeto/{projetoId}")
  public UsuarioDto addUsuarioProjeto(@PathVariable Long usuarioId, @PathVariable Long projetoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.addUsuarioProjeto(usuarioId, projetoId)
    );
  }

  @DeleteMapping("/{usuarioId}/projeto/{projetoId}")
  public UsuarioDto removeUsuarioProjeto(@PathVariable Long usuarioId, @PathVariable Long projetoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.removesuarioProjeto(usuarioId, projetoId)
    );
  }

  @PutMapping("/{usuarioId}/permissao/{permissaoId}")
  public UsuarioDto addUsuarioPermissao(@PathVariable Long usuarioId, @PathVariable Long permissaoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.addUsuarioPermissao(usuarioId, permissaoId)
    );
  }

  @DeleteMapping("/{usuarioId}/permissao/{permissaoId}")
  public UsuarioDto removeUsuarioPermissao(@PathVariable Long usuarioId, @PathVariable Long permissaoId) throws EntityNotFoundException {
    return UsuarioDto.fromEntity(
      usuarioService.removeUsuarioPermissao(usuarioId, permissaoId)
    );
  }
}
