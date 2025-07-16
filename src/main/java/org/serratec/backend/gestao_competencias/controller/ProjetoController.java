package org.serratec.backend.gestao_competencias.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.backend.gestao_competencias.DTO.ColaboradorSimplesDTO;
import org.serratec.backend.gestao_competencias.DTO.ProjetoRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.ProjetoResponseDTO;
import org.serratec.backend.gestao_competencias.entity.Projeto;
import org.serratec.backend.gestao_competencias.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@Tag(name="myOpenAPI")
public class ProjetoController {

    @Autowired
    private ProjetoService projetosService;

//    @PostMapping("/")
//    public Projeto criarProjeto (@RequestBody Projeto projetos) {
//        return projetosService.criarProjeto(projetos);
//    }

    @Operation(summary = "Realiza a CRIACAO de uma PROJETO", method = "POST")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjetoResponseDTO> criarProjeto(@RequestBody ProjetoRequestDTO dto) {
        // Projeto novoProjeto = projetosService.criarProjeto(dto);
         ProjetoResponseDTO projetoCriado = projetosService.criarProjeto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoCriado);
    }

    @Operation(summary = "Realiza uma BUSCA de um PROJETO", method = "GET")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProjetoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(projetosService.listarTodos());
    }

//    @Operation(summary = "Realiza uma ALTERACAO de PROJETO com ID", method = "PUT")
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public ResponseEntity<ProjetoResponseDTO> atualizarProjeto(@PathVariable Long id,
//                                                               @RequestBody ProjetoRequestDTO dto) {
//        ProjetoResponseDTO atualizado = projetosService.atualizarProjeto(id, dto);
//        return ResponseEntity.ok(atualizado);
//    }

    @Operation(summary = "Realiza uma EXCLUSAO de um PROJETO", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sugerir-colaboradores")
    public ResponseEntity<List<ColaboradorSimplesDTO>> sugerirColaboradores(
            @RequestParam("hardSkills") List<Long> hardSkillIds) {
        return ResponseEntity.ok(projetosService.sugerirColaboradoresPorHardSkills(hardSkillIds));
    }


    @PutMapping("/{id}/concluir")
    public ResponseEntity<ProjetoResponseDTO> concluirProjeto(@PathVariable Long id) {
        ProjetoResponseDTO atualizado = projetosService.concluirProjeto(id);
        return ResponseEntity.ok(atualizado);
    }

}
