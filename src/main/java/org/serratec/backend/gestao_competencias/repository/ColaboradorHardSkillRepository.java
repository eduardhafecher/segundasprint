package org.serratec.backend.gestao_competencias.repository;

import org.serratec.backend.gestao_competencias.entity.ColaboradorHardSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorHardSkillRepository extends JpaRepository<ColaboradorHardSkill, Long>{
    int contagemPorHrdSkillId(Long hardSkillId);
}
