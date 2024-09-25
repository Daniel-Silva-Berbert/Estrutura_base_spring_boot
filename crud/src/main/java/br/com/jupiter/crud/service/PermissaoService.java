package br.com.jupiter.crud.service;

import java.util.List;

import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jupiter.crud.entity.Permissao;
import br.com.jupiter.crud.repository.PermissaoRepository;

@Service
public class PermissaoService {
    private PermissaoRepository permissaoRepository;

    @Autowired
    public PermissaoService(PermissaoRepository permissaoRepository)
    {
        this.permissaoRepository = permissaoRepository;
    }
    
    public Permissao salvar(Permissao permissao) {
        
        return permissaoRepository.save(permissao);
    }

    public List<Permissao> getAll() {
        return permissaoRepository.findAll();
    }

    public Permissao getById(Long id) throws EntityNotFoundException  {
        return permissaoRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Permissão", id));
    }

    //verificar depois filtro
    public Permissao getByFilter(String busca) {
            return null;
    }

    public Permissao delete(Long id) throws EntityNotFoundException {
        Permissao permissaoDB = this.getById(id);
        permissaoRepository.deleteById(id);
        return permissaoDB;
    }

    public Permissao editar(Long id, Permissao permissao) throws EntityNotFoundException {
        Permissao permissaoDB = this.getById(id);
        permissaoDB.setNome(permissao.getNome());
        return permissaoRepository.save(permissaoDB);
    }
}
