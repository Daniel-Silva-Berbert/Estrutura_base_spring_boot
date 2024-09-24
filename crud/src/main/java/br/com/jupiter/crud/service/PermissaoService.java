package br.com.jupiter.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public Permissao getById(Long id) throws NotFoundException{
        return permissaoRepository.findById(id).orElseThrow();
    }

    //verificar depois filtro
    public Permissao getByFilter(String busca) {
            return null;
    }

    public Permissao delete(Long id) throws NotFoundException{
        Permissao permissaoDB = this.getById(id);
        permissaoRepository.deleteById(id);
        return permissaoDB;
    }

    public Permissao editar(Long id, Permissao permissao) throws NotFoundException{
        Permissao permissaoDB = this.getById(id);
        permissaoDB.setNome(permissao.getNome());
        return permissaoRepository.save(permissaoDB);
    }
}
