package org.serratec.backend.gestao_competencias.repository;
import org.serratec.backend.gestao_competencias.entity.Gestor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestorRepository  extends JpaRepository<Gestor, Long> {

}
