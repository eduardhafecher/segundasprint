package org.serratec.backend.gestao_competencias.service;

import org.serratec.backend.gestao_competencias.DTO.AnaliseCompetenciaRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.AnaliseCompetenciaResponseDTO;
import org.serratec.backend.gestao_competencias.entity.AnaliseCompetencia;
import org.serratec.backend.gestao_competencias.entity.HardSkill;
import org.serratec.backend.gestao_competencias.enums.StatusAlinhamento;
import org.serratec.backend.gestao_competencias.exception.NotFoundException;
import org.serratec.backend.gestao_competencias.repository.AnaliseCompetenciaRepository;
import org.serratec.backend.gestao_competencias.repository.ColaboradorHardSkillRepository;
import org.serratec.backend.gestao_competencias.repository.HardSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCompetenciaService {

    @Autowired
    private AnaliseCompetenciaRepository analiseRepo;

    @Autowired
    private HardSkillRepository hardSkillRepo;

    @Autowired
    private ColaboradorHardSkillRepository colaboradorHardSkillRepository;

    public AnaliseCompetenciaResponseDTO criarAnalise(AnaliseCompetenciaRequestDTO dto) {
        HardSkill hardSkill = hardSkillRepo.findById(dto.getHardSkillId())
                .orElseThrow(() -> new NotFoundException("Hard Skill não encontrada"));

        // contgm quantos colaboradores possuem essa hardSkill
        int quantidadeAtual = colaboradorHardSkillRepository.countByHardSkillId(dto.getHardSkillId());

        StatusAlinhamento status = calcularStatus(dto.getQuantidadeDesejada(), quantidadeAtual);

        AnaliseCompetencia analise = new AnaliseCompetencia();
        analise.setHardSkill(hardSkill);
        analise.setQuantidadeColaboradoresDesejada(dto.getQuantidadeDesejada());
        analise.setQuantidadeAtual(quantidadeAtual);
        analise.setStatusAlinhamento(status);

        analise = analiseRepo.save(analise);

        return toResponseDTO(analise);
    }

    private StatusAlinhamento calcularStatus(Integer desejada, Integer atual) {
        if (atual < desejada) return StatusAlinhamento.GAP;
        if (atual.equals(desejada)) return StatusAlinhamento.ALINHADO;
        return StatusAlinhamento.EXCESSO;
    }

    private AnaliseCompetenciaResponseDTO toResponseDTO(AnaliseCompetencia analise) {
        AnaliseCompetenciaResponseDTO dto = new AnaliseCompetenciaResponseDTO();
        dto.setId(analise.getId());
        dto.setHardSkillNome(analise.getHardSkill().getNome());
        dto.setQuantidadeDesejada(analise.getQuantidadeColaboradoresDesejada());
        dto.setQuantidadeAtual(analise.getQuantidadeAtual());
        dto.setStatusAlinhamento(analise.getStatusAlinhamento());
        return dto;
    }

    public List<AnaliseCompetenciaResponseDTO> listarTodas() {
        return analiseRepo.findAll().stream().map(this::toResponseDTO).toList();
    }
}
