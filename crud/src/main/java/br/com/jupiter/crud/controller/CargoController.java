package br.com.jupiter.crud.controller;


import br.com.jupiter.crud.controller.dto.CargoCreationDto;
import br.com.jupiter.crud.controller.dto.CargoDto;
import br.com.jupiter.crud.entity.Cargo;
import br.com.jupiter.crud.service.CargoService;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import jakarta.validation.Valid;
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

  @GetMapping
  public List<CargoDto> getAll() {
    List<Cargo> allCargos = cargoService.getAll();
    return allCargos.stream().map(CargoDto::fromEntity).toList();
  }

  @GetMapping("/{id}")
  public CargoDto getById(@PathVariable Long id) throws EntityNotFoundException {
    return CargoDto.fromEntity(
      cargoService.getById(id)
    );
  }

  @GetMapping("search")
  public List<Cargo> getbyName(@RequestParam String cargo) throws NameNotFoundException {
    return cargoService.getByName(cargo);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CargoDto salvar(@RequestBody @Valid  CargoCreationDto cargoCreationDto) {
    return CargoDto.fromEntity(
      cargoService.salvar(cargoCreationDto.toEntity())
    );
  }

  @PutMapping("/{id}")
  public CargoDto editar(@PathVariable Long id, @RequestBody @Valid CargoCreationDto cargoCreationDto) throws EntityNotFoundException {
    return CargoDto.fromEntity(
      cargoService.editar(id, cargoCreationDto.toEntity())
    );
  }

  @DeleteMapping("/{id}")
  public CargoDto delete(@PathVariable Long id) throws EntityNotFoundException {
    return CargoDto.fromEntity(
      cargoService.delete(id)
    );
  }
}
