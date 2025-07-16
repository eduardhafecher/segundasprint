package org.serratec.backend.gestao_competencias.service;

import org.serratec.backend.gestao_competencias.DTO.ProjetoRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.ProjetoResponseDTO;
import org.serratec.backend.gestao_competencias.DTO.ProjetoTopicoRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.ProjetoTopicoResponseDTO;
import org.serratec.backend.gestao_competencias.entity.*;
import org.serratec.backend.gestao_competencias.exception.NotFoundException;
import org.serratec.backend.gestao_competencias.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private HardSkillRepository hardSkillRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ProjetoTopicoRepository projetoTopicoRepository;

    public ProjetoResponseDTO criarProjeto(ProjetoRequestDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());

        projeto = projetoRepository.save(projeto);

        List<ProjetoTopico> requisitos = new ArrayList<>();
        List<Long> hardSkillIdsRequisitadas = new ArrayList<>();
        List<Long> topicoIdsRequisitados = new ArrayList<>();

        if (dto.getTopicos() != null) {
            for (ProjetoTopicoRequestDTO req : dto.getTopicos()) {
                HardSkill hs = hardSkillRepository.findById(req.getHardSkillId())
                        .orElseThrow(() -> new NotFoundException("Hard Skill não encontrada"));

                List<Topico> topicos = topicoRepository.findAllById(req.getTopicoIds());

                ProjetoTopico requisito = new ProjetoTopico();
                requisito.setProjeto(projeto);
                requisito.setHardSkill(hs);
                requisito.setTopicos(topicos);

                requisitos.add(requisito);

                hardSkillIdsRequisitadas.add(hs.getId());
                for (Topico t : topicos) {
                    topicoIdsRequisitados.add(t.getId());
                }
            }

            projetoTopicoRepository.saveAll(requisitos);
            projeto.setRequisitos(requisitos);
        }

        // Filtrando colaboradores aptos com base em ColaboradorHardSkill
        if (dto.getColaboradoresIds() != null && !dto.getColaboradoresIds().isEmpty()) {
            List<Colaborador> colaboradores = colaboradorRepository.findAllById(dto.getColaboradoresIds());
            List<Colaborador> aptos = new ArrayList<>();

            for (Colaborador c : colaboradores) {
                Set<Long> hardSkillIds = c.getHardSkillsAssociadas().stream()
                        .map(ColaboradorHardSkill::getHardSkill)
                        .map(HardSkill::getId)
                        .collect(Collectors.toSet());

                Set<Long> topicoIds = c.getHardSkillsAssociadas().stream()
                        .flatMap(chs -> chs.getTopicosDominados().stream())
                        .map(Topico::getId)
                        .collect(Collectors.toSet());

                boolean temTodasHS = hardSkillIds.containsAll(hardSkillIdsRequisitadas);
                boolean temTodosTopicos = topicoIds.containsAll(topicoIdsRequisitados);

                if (temTodasHS && temTodosTopicos) {
                    aptos.add(c);
                }
            }

            projeto.setColaboradores(aptos);
        }

        return toResponseDTO(projeto);
    }

    private ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        ProjetoResponseDTO dto = new ProjetoResponseDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());

        List<ProjetoTopicoResponseDTO> requisitosDTO = new ArrayList<>();
        if (projeto.getRequisitos() != null) {
            for (ProjetoTopico pr : projeto.getRequisitos()) {
                ProjetoTopicoResponseDTO prDto = new ProjetoTopicoResponseDTO();
                prDto.setHardSkillId(pr.getHardSkill().getId());
                prDto.setHardSkillNome(pr.getHardSkill().getNome());

                List<String> topicoNomes = pr.getTopicos().stream()
                        .map(Topico::getNome)
                        .toList();

                prDto.setTopicos(topicoNomes);
                requisitosDTO.add(prDto);
            }
        }

        dto.setRequisitos(requisitosDTO);
        return dto;
    }

    public void deletar(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Projeto não encontrado com ID: " + id));
        projetoRepository.delete(projeto);
    }

    public List<ProjetoResponseDTO> listarTodos() {
        List<Projeto> projetos = projetoRepository.findAll();
        List<ProjetoResponseDTO> dtos = new ArrayList<>();
        for (Projeto projeto : projetos) {
            dtos.add(toResponseDTO(projeto));
        }
        return dtos;
    }
}
