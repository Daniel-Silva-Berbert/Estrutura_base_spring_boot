package br.com.jupiter.crud.service;

import java.util.List;

import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.jupiter.crud.entity.Cargo;
import br.com.jupiter.crud.repository.CargoRepository;

@Service
public class CargoService {
    private CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository)
    {
        this.cargoRepository = cargoRepository;
    }
    
    public Cargo salvar(Cargo cargo) {
        
        return cargoRepository.save(cargo);
    }

    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    public Cargo getById(Long id) throws EntityNotFoundException {
        return cargoRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Cargo", id));
    }

    //verificar depois filtro
    public Cargo getByFilter(String busca) {
            return null;
    }

    public Cargo delete(Long id) throws EntityNotFoundException {
        Cargo cargoDB = this.getById(id);
        cargoRepository.deleteById(id);
        return cargoDB;
    }

    public Cargo editar(Long id, Cargo cargo) throws EntityNotFoundException {
        Cargo cargoDB = this.getById(id);
        cargoDB.setNome(cargo.getNome());
        cargoDB.setRemuneracao(cargo.getRemuneracao());
        return cargoRepository.save(cargoDB);
    }
}
