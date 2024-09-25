package br.com.jupiter.crud.controller;


import br.com.jupiter.crud.controller.dto.CargoDto;
import br.com.jupiter.crud.entity.Cargo;
import br.com.jupiter.crud.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
