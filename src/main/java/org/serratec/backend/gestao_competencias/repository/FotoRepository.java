package org.serratec.backend.gestao_competencias.repository;
import org.serratec.backend.gestao_competencias.entity.Colaborador;
import org.serratec.backend.gestao_competencias.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    Optional<Foto> findByColaborador(Colaborador colaborador);
}
