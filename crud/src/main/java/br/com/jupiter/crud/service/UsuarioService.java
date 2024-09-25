package br.com.jupiter.crud.service;

import java.util.List;

import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import br.com.jupiter.crud.entity.Usuario;
import br.com.jupiter.crud.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository cargoRepository)
    {
        this.usuarioRepository = cargoRepository;
    }
    
    public Usuario salvar(Usuario usuario) {
        
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) throws EntityNotFoundException  {
        return usuarioRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Usuario", id));
    }

    //verificar depois filtro
    public Usuario getByFilter(String busca) {
            return null;
    }

    public Usuario delete(Long id) throws EntityNotFoundException {
        Usuario usuarioDB = this.getById(id);
        usuarioRepository.deleteById(id);
        return usuarioDB;
    }

    public Usuario editar(Long id, Usuario usuario) throws EntityNotFoundException {
        Usuario usuarioDB = this.getById(id);
        usuarioDB.setNome(usuario.getNome());
        usuarioDB.setUserName(usuario.getUserName());
        usuarioDB.setEmail(usuario.getEmail());
        usuarioDB.setPassword(usuario.getPassword());
        usuarioDB.setPermissoes(usuario.getPermissoes());
        usuarioDB.setCargo(usuario.getCargo());
        usuarioDB.setProjeto(usuario.getProjeto());
        return usuarioRepository.save(usuarioDB);
    }
}
