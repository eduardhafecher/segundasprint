package org.serratec.backend.gestao_competencias.controller;

import java.util.List;

import org.serratec.backend.gestao_competencias.DTO.HardSkillRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.HardSkillResponseDTO;
import org.serratec.backend.gestao_competencias.entity.HardSkill;
import org.serratec.backend.gestao_competencias.service.HardSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hardskill")
public class HardSkillController {

    @Autowired
    private HardSkillService hardSkillService;

    @PostMapping
    public ResponseEntity<HardSkillResponseDTO> criar(@RequestBody HardSkillRequestDTO dto) {
        return (ResponseEntity<HardSkillResponseDTO>) ResponseEntity.ok(hardSkillService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<HardSkill>> listar() {
        return ResponseEntity.ok(hardSkillService.listarTodas());
    }

}