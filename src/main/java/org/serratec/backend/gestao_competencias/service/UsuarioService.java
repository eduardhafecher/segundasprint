package org.serratec.backend.gestao_competencias.service;

import jakarta.transaction.Transactional;
import org.serratec.backend.gestao_competencias.DTO.UsuarioRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.UsuarioResponseDTO;
import org.serratec.backend.gestao_competencias.entity.Usuario;
import org.serratec.backend.gestao_competencias.entity.UsuarioPerfil;
import org.serratec.backend.gestao_competencias.exception.UsuarioException;
import org.serratec.backend.gestao_competencias.repository.UsuarioPerfilRepository;
import org.serratec.backend.gestao_competencias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private MailConfig mailConfig;


    public List<UsuarioResponseDTO> listar() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioResponseDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosDTO.add(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
        }
        return usuariosDTO;
    }

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO usuario) {
        Optional<Usuario> u = repository.findByEmail(usuario.getEmail());
        if (u.isPresent()) {
            throw new UsuarioException("Email já cadastrado!");
        }
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioEntity.setSenha(usuario.getSenha());

        for (UsuarioPerfil up : usuario.getUsuarioPerfis()) {
            up.setPerfil(perfilService.buscar(up.getPerfil().getId()));
            up.setUsuario(usuarioEntity);
            up.setDataCriacao(LocalDate.now());
        }
        usuarioEntity = repository.save(usuarioEntity);
        usuarioPerfilRepository.saveAll(usuario.getUsuarioPerfis());

        //mailConfig.enviar(usuarioEntity.getEmail(),"Confimação de cadastro", usuario.toString());

        return new UsuarioResponseDTO(usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getEmail());
    }
}
