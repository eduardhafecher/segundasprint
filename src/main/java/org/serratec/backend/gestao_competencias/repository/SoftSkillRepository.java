package org.serratec.backend.gestao_competencias.repository;

import org.serratec.backend.gestao_competencias.entity.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoftSkillRepository extends JpaRepository<SoftSkill,Long> {
    Optional<SoftSkill> findById(Long aLong);
}