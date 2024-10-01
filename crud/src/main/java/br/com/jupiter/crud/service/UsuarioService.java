package br.com.jupiter.crud.service;

import java.util.List;

import br.com.jupiter.crud.entity.Cargo;
import br.com.jupiter.crud.entity.Permissao;
import br.com.jupiter.crud.entity.Projeto;
import br.com.jupiter.crud.service.exception.EntityNotFoundException;
import br.com.jupiter.crud.service.exception.NameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.jupiter.crud.entity.Usuario;
import br.com.jupiter.crud.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    private CargoService cargoService;
    private ProjetoService projetoService;
    private PermissaoService permissaoService;


    @Autowired
    public UsuarioService(
      UsuarioRepository usuarioRepository,
      CargoService cargoService,
      ProjetoService projetoService,
      PermissaoService permissaoService
    )
    {
        this.usuarioRepository = usuarioRepository;
        this.cargoService = cargoService;
        this.projetoService = projetoService;
        this.permissaoService = permissaoService;
    }
    
    public Usuario salvar(Usuario usuario) {
        String hashedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());

        usuario.setPassword(hashedPassword);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) throws EntityNotFoundException  {
        return usuarioRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Usuario", id));
    }

    public List<Usuario> getByName(String usuario) throws NameNotFoundException {
        List<Usuario> usuarios = usuarioRepository.findByNomeContainingIgnoreCase(usuario);
        if(usuarios.isEmpty()) {
            throw new NameNotFoundException("Usuario", usuario);
        }
        return usuarios;
    }

    public Usuario delete(Long id) throws EntityNotFoundException {
        Usuario usuarioDB = this.getById(id);
        usuarioRepository.deleteById(id);
        return usuarioDB;
    }

    public Usuario editar(Long id, Usuario usuario) throws EntityNotFoundException {
        this.getById(id);
        return usuarioRepository.save(usuario);
    }

    public Usuario setUsuarioCargo(Long usuarioId, Long cargoId) throws EntityNotFoundException {
        Usuario usuario = getById(usuarioId);
        Cargo cargo = cargoService.getById(cargoId);

        usuario.setCargo(cargo);

        return usuarioRepository.save(usuario);
    }

    public Usuario removeUsuarioCargo(Long usuarioId) throws EntityNotFoundException {
        Usuario usuario = getById(usuarioId);

        usuario.setCargo(null);

        return usuarioRepository.save(usuario);
    }

    public Usuario addUsuarioProjeto(Long usarioId, Long projetoId) throws EntityNotFoundException {
        Usuario usuario = getById(usarioId);

        Projeto projeto = projetoService.getById(projetoId);

        usuario.getProjetos().add(projeto);

        return usuarioRepository.save(usuario);
    }

    public Usuario removesuarioProjeto(Long usarioId, Long projetoId) throws EntityNotFoundException {
        Usuario usuario = getById(usarioId);

        Projeto projeto = projetoService.getById(projetoId);

        usuario.getProjetos().remove(projeto);

        return usuarioRepository.save(usuario);
    }

    public Usuario addUsuarioPermissao(Long usarioId, Long permissaoId) throws EntityNotFoundException {
        Usuario usuario = getById(usarioId);

        Permissao permissao = permissaoService.getById(permissaoId);

        permissao.getUsuarios().add(usuario);

        return usuarioRepository.save(usuario);
    }

    public Usuario removeUsuarioPermissao(Long usarioId, Long permissaoId) throws EntityNotFoundException {
        Usuario usuario = getById(usarioId);

        Permissao permissao = permissaoService.getById(permissaoId);

        permissao.getUsuarios().remove(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return usuarioRepository.findByUserName(userName)
          .orElseThrow(() -> new UsernameNotFoundException(userName));
    }
}
