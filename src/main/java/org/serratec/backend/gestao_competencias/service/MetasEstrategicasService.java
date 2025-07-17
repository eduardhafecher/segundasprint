package org.serratec.backend.gestao_competencias.service;

import java.util.List;

import org.serratec.backend.gestao_competencias.DTO.MetasEstrategicasRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.MetasEstrategicasResponseDTO;
import org.serratec.backend.gestao_competencias.entity.HardSkill;
import org.serratec.backend.gestao_competencias.entity.MetasEstrategicas;
import org.serratec.backend.gestao_competencias.repository.HardSkillRepository;
import org.serratec.backend.gestao_competencias.repository.MetasEstrategicasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetasEstrategicasService {

//	puxei metasestrategicasrepository e hardskillrepository
	    @Autowired
	    private MetasEstrategicasRepository metasRepo;

	    @Autowired
	    private HardSkillRepository hardSkillRepo;

//	    metodo para salvar meta estrategica
	    public MetasEstrategicasResponseDTO salvar(MetasEstrategicasRequestDTO dto) {
	        MetasEstrategicas meta = new MetasEstrategicas();
	        meta.setDescricao(dto.getDescricao());
	        meta.setPrazo(dto.getPrazo());
	        meta.setStatusMeta(dto.getStatusMeta());
	        meta.setNivelPrioridade(dto.getNivelPrioridade());
	        meta.setNivelDeDominio(dto.getNivelDeDominio());

	        HardSkill hs = hardSkillRepo.findById(dto.getHardSkillId())
	            .orElseThrow(() -> new RuntimeException("Hard skill não encontrada"));
	        meta.setHardSkill(hs);
	        metasRepo.save(meta);
	        MetasEstrategicasResponseDTO response = new MetasEstrategicasResponseDTO();
	        response.setId(meta.getId());
	        response.setDescricao(meta.getDescricao());
	        response.setPrazo(meta.getPrazo());
	        response.setStatusMeta(meta.getStatusMeta());
	        response.setNivelPrioridade(meta.getNivelPrioridade());
	        response.setNivelDeDominio(meta.getNivelDeDominio());
	        response.setNomeHardSkill(hs.getNome());

	        return response;
	    }

//	    metodo para listar todas as metas
	    public List<MetasEstrategicasResponseDTO> listarTodas() {
	        List<MetasEstrategicas> metas = metasRepo.findAll();

	        return metas.stream().map(meta -> {
	            MetasEstrategicasResponseDTO dto = new MetasEstrategicasResponseDTO();
	            dto.setId(meta.getId());
	            dto.setDescricao(meta.getDescricao());
	            dto.setPrazo(meta.getPrazo());
	            dto.setStatusMeta(meta.getStatusMeta());
	            dto.setNivelPrioridade(meta.getNivelPrioridade());
	            dto.setNivelDeDominio(meta.getNivelDeDominio());
	            dto.setNomeHardSkill(meta.getHardSkill().getNome());
	            return dto;
	        }).toList();
	    }
//	    mtodo para buscar uma meta por id
	    public MetasEstrategicasResponseDTO buscarPorId(Long id) {
	        MetasEstrategicas meta = metasRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Meta Estrategica não encontrada"));

	        MetasEstrategicasResponseDTO dto = new MetasEstrategicasResponseDTO();
	        dto.setId(meta.getId());
	        dto.setDescricao(meta.getDescricao());
	        dto.setPrazo(meta.getPrazo());
	        dto.setStatusMeta(meta.getStatusMeta());
	        dto.setNivelPrioridade(meta.getNivelPrioridade());
	        dto.setNivelDeDominio(meta.getNivelDeDominio());
	        dto.setNomeHardSkill(meta.getHardSkill().getNome());
	        return dto;
	    }
//	    metodo para atualizar uma meta put
	    public MetasEstrategicasResponseDTO atualizar(Long id, MetasEstrategicasRequestDTO dto) {
	        MetasEstrategicas meta = metasRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Meta Estrategica não encontrada"));

	        meta.setDescricao(dto.getDescricao());
	        meta.setPrazo(dto.getPrazo());
	        meta.setStatusMeta(dto.getStatusMeta());
	        meta.setNivelPrioridade(dto.getNivelPrioridade());
	        meta.setNivelDeDominio(dto.getNivelDeDominio());

	        HardSkill hs = hardSkillRepo.findById(dto.getHardSkillId())
	            .orElseThrow(() -> new RuntimeException("Hard skill não encontrada"));
	        meta.setHardSkill(hs);

	        metasRepo.save(meta);

	        MetasEstrategicasResponseDTO response = new MetasEstrategicasResponseDTO();
	        response.setId(meta.getId());
	        response.setDescricao(meta.getDescricao());
	        response.setPrazo(meta.getPrazo());
	        response.setStatusMeta(meta.getStatusMeta());
	        response.setNivelPrioridade(meta.getNivelPrioridade());
	        response.setNivelDeDominio(meta.getNivelDeDominio());
	        response.setNomeHardSkill(hs.getNome());

	        return response;
	    }
//	    metodo para deletar meta
	    public void deletar(Long id) {
	        if (!metasRepo.existsById(id)) {
	            throw new RuntimeException("Meta EStrategica não encontrada");
	        }
	        metasRepo.deleteById(id);
	    }
//metodo para salvar lista de metas
	    public List<MetasEstrategicasResponseDTO> salvarLista(List<MetasEstrategicasRequestDTO> dtos) {
	        return dtos.stream()
	                   .map(this::salvar)
	                   .toList();
	    }

	}