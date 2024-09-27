package br.com.jupiter.crud.controller;

import br.com.jupiter.crud.controller.dto.ProjetoCreationDto;
import br.com.jupiter.crud.controller.dto.ProjetoDto;
import br.com.jupiter.crud.entity.Projeto;
import br.com.jupiter.crud.service.ProjetoService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

  private ProjetoService projetoService;

  @Autowired
  public ProjetoController(ProjetoService projetoService) {
    this.projetoService = projetoService;
  }

  @GetMapping
  public List<ProjetoDto> getAll() {
    List<Projeto> allProjetos = projetoService.getAll();
    return allProjetos.stream().map(ProjetoDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public ProjetoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return ProjetoDto.fromEntity(
      projetoService.getById(id)
    );
  }

  @GetMapping("search")
  public List<Projeto> getByName(@RequestParam String projeto) throws NameNotFoundException {
    return projetoService.getByName(projeto);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProjetoDto salvar(@RequestBody @Valid ProjetoCreationDto projetoCreationDto) {
    return ProjetoDto.fromEntity(
      projetoService.salvar(projetoCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public ProjetoDto editar(@PathVariable Long id, @RequestBody @Valid ProjetoCreationDto projetoCreationDto) throws EntityNotFoundException {
    return ProjetoDto.fromEntity(
      projetoService.editar(id, projetoCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public ProjetoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return ProjetoDto.fromEntity(
      projetoService.delete(id)
    );
  }
}
