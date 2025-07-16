package org.serratec.backend.gestao_competencias.repository;
import jakarta.persistence.Id;
import org.serratec.backend.gestao_competencias.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    List<Colaborador> id(Long id);
    Colaborador findByEmail(String email);
    Colaborador findByNome(String nome);
    Optional<Colaborador> findByCpf(String cpf);

    Id Id(Long id);

    List<Colaborador> nome(String nome);
}
