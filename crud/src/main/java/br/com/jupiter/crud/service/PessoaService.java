package br.com.jupiter.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jupiter.crud.entity.Pessoa;
import br.com.jupiter.crud.repository.PessoaRepository;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;

@Service
public class PessoaService {
private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository)
    {
        this.pessoaRepository = pessoaRepository;
    }
    
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa getById(Long id) throws EntityNotFoundException {
        return pessoaRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Pessoa", id));
    }

    public List<Pessoa> getByName(String pessoa) throws NameNotFoundException {
        List<Pessoa> pessoas = pessoaRepository.findByNomeContainingIgnoreCase(pessoa);
        if (pessoas.isEmpty()) {
            throw new NameNotFoundException("Pessoa", pessoa);
        }
        return pessoas;
    }

    public Pessoa delete(Long id) throws EntityNotFoundException {
        Pessoa pessoaDB = this.getById(id);
        pessoaRepository.deleteById(id);
        return pessoaDB;
    }

    public Pessoa editar(Long id, Pessoa pessoa) throws EntityNotFoundException {
        Pessoa pessoaDB = this.getById(id);
        return pessoaRepository.save(pessoa);
    }
}
