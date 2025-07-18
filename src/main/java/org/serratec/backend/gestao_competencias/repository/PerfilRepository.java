package org.serratec.backend.gestao_competencias.repository;

import org.serratec.backend.gestao_competencias.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
