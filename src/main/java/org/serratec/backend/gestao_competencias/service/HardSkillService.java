package org.serratec.backend.gestao_competencias.service;

import java.util.List;

import org.serratec.backend.gestao_competencias.DTO.TopicoResponseDTO;
import org.serratec.backend.gestao_competencias.DTO.HardSkillRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.HardSkillResponseDTO;
import org.serratec.backend.gestao_competencias.entity.Topico;
import org.serratec.backend.gestao_competencias.entity.HardSkill;
import org.serratec.backend.gestao_competencias.repository.TopicoRepository;
import org.serratec.backend.gestao_competencias.repository.HardSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardSkillService {

        @Autowired
        private HardSkillRepository repository;
        @Autowired
        private TopicoRepository assuntosRepository;

    public HardSkillResponseDTO criar(HardSkillRequestDTO dto) {
        HardSkill hardSkill = new HardSkill();
        hardSkill.setNome(dto.getNome());
        hardSkill = repository.save(hardSkill);
        repository.flush();
        
        final HardSkill hardSkillFinal = hardSkill;

        if (dto.getTopicos() != null) {
            List<Topico> listaTopicos = dto.getTopicos().stream().map(nome -> {
                Topico topico = new Topico();
                topico.setNome(nome);
                topico.setHardSkill(hardSkillFinal);
                return topico;
            }).toList();

            assuntosRepository.saveAll(listaTopicos);
            hardSkill.setTopicos(listaTopicos);
        }

        List<TopicoResponseDTO> topicosDTO = hardSkill.getTopicos().stream()
            .map(a -> new TopicoResponseDTO(a.getNome()))
            .toList();

        HardSkillResponseDTO responseDTO = new HardSkillResponseDTO();
        responseDTO.setId(hardSkill.getId());
        responseDTO.setNome(hardSkill.getNome());
        responseDTO.setTopicos(topicosDTO);

        return responseDTO;
    }

        public List<HardSkill> listarTodas() {
            return repository.findAll();
        }
    }