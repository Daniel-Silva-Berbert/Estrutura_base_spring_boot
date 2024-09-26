package br.com.jupiter.crud.controller;


import br.com.jupiter.crud.controller.dto.CargoCreationDto;
import br.com.jupiter.crud.controller.dto.CargoDto;
import br.com.jupiter.crud.entity.Cargo;
import br.com.jupiter.crud.service.CargoService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargoController {

  private CargoService cargoService;

  @Autowired
  public CargoController(CargoService cargoService) {
    this.cargoService = cargoService;
  }

  @Operation(summary = "Retorna todos os cargos.")
  @GetMapping
  public List<CargoDto> getAll() {
    List<Cargo> allCargos = cargoService.getAll();
    return allCargos.stream().map(CargoDto::fromEntity).toList();
  }

  @Operation(summary = "Retorna um cargo especifico com base no ID.", description = "Retorna um cargo específico com base no ID fornecido como parâmetro.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "ID do cargo encontrado"),
    @ApiResponse(responseCode = "404", description = "ID do cargo não encontrado")
  })
  @GetMapping("/{id}")
  public CargoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return CargoDto.fromEntity(
      cargoService.getById(id)
    );
  }

  @Operation(summary = "Retorna um cargo especifico com base na String.", description = "Retorna um cargo específico com base na String fornecido como parâmetro no search.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Nome do cargo encontrado"),
    @ApiResponse(responseCode = "404", description = "Nome do cargo não encontrado")
  })
  @GetMapping("search")
  public List<Cargo> getbyName(@RequestParam String cargo) throws NameNotFoundException {
    return cargoService.getByName(cargo);
  }

  @Operation(summary = "Criar um novo cargo.")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CargoDto salvar(@RequestBody CargoCreationDto cargoCreationDto) {
    return CargoDto.fromEntity(
      cargoService.salvar(cargoCreationDto.toEntity())
    );
  }

  @Operation(summary = "Atualizar um cargo.", description = "Atualizar um cargo com base no ID fornecido como parâmetro.")
  @PutMapping("/{id}")
  public CargoDto editar(@PathVariable Long id, @RequestBody CargoCreationDto cargoCreationDto) throws EntityNotFoundException {
    return CargoDto.fromEntity(
      cargoService.editar(id, cargoCreationDto.toEntity())
    );
  }

  @Operation(summary = "Deleta um cargo.", description = "Deleta um cargo com base no ID fornecido como parâmetro.")
  @DeleteMapping("/{id}")
  public CargoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return CargoDto.fromEntity(
      cargoService.delete(id)
    );
  }
}
