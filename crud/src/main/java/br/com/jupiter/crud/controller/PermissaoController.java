package br.com.jupiter.crud.controller;

import br.com.jupiter.crud.controller.dto.PermissaoCreationDto;
import br.com.jupiter.crud.controller.dto.PermissaoDto;
import br.com.jupiter.crud.entity.Permissao;
import br.com.jupiter.crud.service.PermissaoService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

  private PermissaoService permissaoService;

  @Autowired
  public PermissaoController(PermissaoService permissaoService) {
    this.permissaoService = permissaoService;
  }

  @GetMapping
  public List<PermissaoDto> getAll() {
    List<Permissao> allPermissoes = permissaoService.getAll();
    return allPermissoes.stream().map(PermissaoDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public PermissaoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.getById(id)
    );
  }

  @GetMapping("search")
  public List<Permissao> getbyName(@RequestParam String permissao) throws NameNotFoundException {
    return permissaoService.getByName(permissao);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PermissaoDto salvar(@RequestBody PermissaoCreationDto permissaoCreationDto) {
    return PermissaoDto.fromEntity(
      permissaoService.salvar(permissaoCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public PermissaoDto editar(@PathVariable Long id, @RequestBody PermissaoCreationDto permissaoCreationDto) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.editar(id, permissaoCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public PermissaoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return PermissaoDto.fromEntity(
      permissaoService.delete(id)
    );
  }
}
