package org.serratec.backend.gestao_competencias.service;

import jakarta.transaction.Transactional;
import org.serratec.backend.gestao_competencias.DTO.*;
import org.serratec.backend.gestao_competencias.entity.*;
import org.serratec.backend.gestao_competencias.exception.ColaboradorException;
import org.serratec.backend.gestao_competencias.exception.NotFoundException;
import org.serratec.backend.gestao_competencias.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private SoftSkillRepository softSkillRepository;

    @Autowired
    private HardSkillRepository hardSkillRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ColaboradorHardSkillRepository colaboradorHardSkillRepository;

    private Colaborador toEntity(ColaboradorRequestDTO dto) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(dto.getNome());
        colaborador.setCpf(dto.getCpf());
        colaborador.setEmail(dto.getEmail());
        colaborador.setPassword(dto.getPassword());
        colaborador.setOcupacao(dto.getOcupacao());

        // primeiro teste: associar as skills aqui, mas o DTO de requisição não tem essa informação
        // Isso deve ser feito em outro metodo, depois que o colaborador já existir
        // if (dto.getSoftSkillIds() != null) { ... }

        return colaborador;
    }


    private ColaboradorResponseDTO toResponseDTO(Colaborador colaborador) {
        ColaboradorResponseDTO dto = new ColaboradorResponseDTO();
        dto.setId(colaborador.getId());
        dto.setNome(colaborador.getNome());
        dto.setEmail(colaborador.getEmail());
        dto.setOcupacao(colaborador.getOcupacao());

        // mapeia as associações de hard skills
        if (colaborador.getHardSkillsAssociadas() != null) {
            List<ColaboradorHardSkillResponseDTO> hardSkillsDTO = colaborador.getHardSkillsAssociadas().stream()
                    .map(this::calcularPercentualEGerarResponse)
                    .collect(Collectors.toList());
            dto.setHardSkills(hardSkillsDTO);
        }

        // mapeia as soft
        if (colaborador.getSoftSkills() != null) {
            List<SoftSkillDTO> softSkillsDTO = colaborador.getSoftSkills().stream()
                    .map(softSkill -> new SoftSkillDTO(softSkill.getId(), softSkill.getNome()))
                    .collect(Collectors.toList());
            dto.setSoftSkills(softSkillsDTO);
        }

        return dto;
    }


    public List<ColaboradorResponseDTO> listarTodos() {
        return colaboradorRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ColaboradorResponseDTO buscarPorId(Long id) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado."));
        return toResponseDTO(colaborador);
    }

    public ColaboradorResponseDTO inserir(ColaboradorRequestDTO dto) {
        // validacao cpf
        if (colaboradorRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new ColaboradorException("CPF já cadastrado.");
        }
        Colaborador colaborador = toEntity(dto);
        colaboradorRepository.save(colaborador);
        return toResponseDTO(colaborador);
    }

    @Transactional
    public ColaboradorResponseDTO atualizar(Long id, ColaboradorRequestDTO dto) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado."));

        colaborador.setNome(dto.getNome());
        colaborador.setOcupacao(dto.getOcupacao());
        colaborador.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            colaborador.setPassword(dto.getPassword());
        }
        colaboradorRepository.save(colaborador);
        return toResponseDTO(colaborador);
    }

    public void remover(Long id) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado."));
        colaboradorRepository.delete(colaborador);
    }

    @Transactional
    public ColaboradorResponseDTO adicionarSoftSkills(Long colaboradorId, List<Long> softSkillIds) {
        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado."));

        List<SoftSkill> novasSkills = softSkillRepository.findAllById(softSkillIds);
        // Evitar duplicatas
        novasSkills.forEach(skill -> {
            if (!colaborador.getSoftSkills().contains(skill)) {
                colaborador.getSoftSkills().add(skill);
            }
        });

        colaboradorRepository.save(colaborador);
        return toResponseDTO(colaborador);
    }

//    public ColaboradorResponseDTO adicionarHardSkills(Long colaboradorId, List<HardSkillRequestDTO> hardSkillsDTO) {
//        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
//                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado"));
//
//        List<HardSkill> novas = hardSkillsDTO.stream().map(dto -> {
//            HardSkill hs = new HardSkill();
//            hs.setNome(dto.getNome());
//            hs.setNivelCompetencia(dto.getNivelCompetencia());
////            colaborador.getHardSkills().addAll(hardSkills);
//            return hs;
//        }).toList();
//
//        colaborador.getHardSkills().addAll(novas);
//        colaboradorRepository.save(colaborador);
//
//        return toColaboradorResponseDTO(colaborador);
//    }

    // associar uma hard skill a um colab
    @Transactional
    public ColaboradorHardSkillResponseDTO associarHardSkill(Long colaboradorId, AssociarHardSkillRequestDTO requestDTO) {
        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new NotFoundException("Colaborador não encontrado com ID: " + colaboradorId));

        HardSkill hardSkill = hardSkillRepository.findById(requestDTO.getHardSkillId())
                .orElseThrow(() -> new NotFoundException("HardSkill não encontrada com ID: " + requestDTO.getHardSkillId()));

        List<Topico> topicosDominados = topicoRepository.findAllById(requestDTO.getTopicosDominadosIds());

        // validando p garantir q os topicos pertencem à hardskill
        for (Topico topico : topicosDominados) {
            if (!topico.getHardSkill().getId().equals(hardSkill.getId())) {
                throw new IllegalArgumentException("O tópico '" + topico.getNome() + "' não pertence à HardSkill '" + hardSkill.getNome() + "'.");
            }
        }

        // erifica p evitar duplicates
        ColaboradorHardSkill associacao = colaborador.getHardSkillsAssociadas().stream()
                .filter(a -> a.getHardSkill().getId().equals(hardSkill.getId()))
                .findFirst()
                .orElse(null);

        if (associacao == null) {
            associacao = new ColaboradorHardSkill();
            associacao.setColaborador(colaborador);
            associacao.setHardSkill(hardSkill);
            colaborador.getHardSkillsAssociadas().add(associacao);
        }

        associacao.setTopicosDominados(topicosDominados);

        colaboradorHardSkillRepository.save(associacao);

        return calcularPercentualEGerarResponse(associacao);
    }

    private ColaboradorHardSkillResponseDTO calcularPercentualEGerarResponse(ColaboradorHardSkill associacao) {
        HardSkill hardSkill = associacao.getHardSkill();

        int totalTopicos = hardSkillRepository.findById(hardSkill.getId()).get().getTopicos().size();
        int qtdTopicosDominados = associacao.getTopicosDominados().size();

        // erro comum: fiz a conta com inteiros, o que fazia o resultado ser sempre 0.
        // Tem q fazer o casting para double pra divisão decimal funcionar corretamente.
        double percentual = (totalTopicos > 0) ? ((double) qtdTopicosDominados / totalTopicos) * 100.0 : 0.0;

        List<String> nomesTopicosDominados = associacao.getTopicosDominados().stream()
                .map(Topico::getNome)
                .collect(Collectors.toList());

        ColaboradorHardSkillResponseDTO dto = new ColaboradorHardSkillResponseDTO();
        dto.setHardSkillId(hardSkill.getId());
        dto.setNomeHardSkill(hardSkill.getNome());
        dto.setTotalTopicos(totalTopicos);
        dto.setTopicosDominados(qtdTopicosDominados);
        dto.setPercentualDominio(percentual);
        dto.setTopicosDominadosNomes(nomesTopicosDominados);

        return dto;
    }


}