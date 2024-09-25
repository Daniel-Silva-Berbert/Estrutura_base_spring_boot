package br.com.jupiter.crud.service;

import java.util.List;

import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jupiter.crud.entity.Projeto;
import br.com.jupiter.crud.repository.ProjetoRepository;

@Service
public class ProjetoService {
    private ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository)
    {
        this.projetoRepository = projetoRepository;
    }
    
    public Projeto salvar(Projeto projeto) {
        
        return projetoRepository.save(projeto);
    }

    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    public Projeto getById(Long id) throws EntityNotFoundException {
        return projetoRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Projeto", id));
    }

    //verificar depois filtro
    public Projeto getByFilter(String busca) {
            return null;
    }

    public Projeto delete(Long id) throws EntityNotFoundException {
        Projeto projetoDB = this.getById(id);
        projetoRepository.deleteById(id);
        return projetoDB;
    }

    public Projeto editar(Long id, Projeto projeto) throws EntityNotFoundException {
        this.getById(id);
        return projetoRepository.save(projeto);
    }
}
