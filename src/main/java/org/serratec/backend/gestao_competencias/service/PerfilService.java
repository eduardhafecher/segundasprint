package org.serratec.backend.gestao_competencias.service;

import org.serratec.backend.gestao_competencias.entity.Perfil;
import org.serratec.backend.gestao_competencias.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    public Perfil buscar(Long id) {
        Optional<Perfil> perfil = repository.findById(id);
        return perfil.get();
    }
}
