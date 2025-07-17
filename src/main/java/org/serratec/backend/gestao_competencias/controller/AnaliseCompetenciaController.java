package org.serratec.backend.gestao_competencias.controller;

import org.serratec.backend.gestao_competencias.DTO.AnaliseCompetenciaRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.AnaliseCompetenciaResponseDTO;
import org.serratec.backend.gestao_competencias.service.AnaliseCompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analise")
public class AnaliseCompetenciaController {

    @Autowired
    private AnaliseCompetenciaService service;

    @PostMapping
    public AnaliseCompetenciaResponseDTO criar(@RequestBody AnaliseCompetenciaRequestDTO dto) {
        return service.criarAnalise(dto);
    }

    @GetMapping
    public List<AnaliseCompetenciaResponseDTO> listar() {
        return service.listarTodas();
    }
}

