package org.serratec.backend.gestao_competencias.controller;

import java.util.List;

import org.serratec.backend.gestao_competencias.DTO.MetasEstrategicasRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.MetasEstrategicasResponseDTO;
import org.serratec.backend.gestao_competencias.service.MetasEstrategicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metas")
public class MetasEstrategicasController {

	    @Autowired
	    private MetasEstrategicasService metasService;

	    @GetMapping
	    public ResponseEntity<List<MetasEstrategicasResponseDTO>> listarTodas() {
	        return ResponseEntity.ok(metasService.listarTodas());
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<MetasEstrategicasResponseDTO> buscarPorId(@PathVariable Long id) {
	        return ResponseEntity.ok(metasService.buscarPorId(id));
	    }

	    @PostMapping
	    public ResponseEntity<MetasEstrategicasResponseDTO> criar(@RequestBody MetasEstrategicasRequestDTO dto) {
	        return new ResponseEntity<>(metasService.salvar(dto), HttpStatus.CREATED);
	    }
//	    post de lista
	    @PostMapping("/lista")
	    public ResponseEntity<List<MetasEstrategicasResponseDTO>> criarEmLote(@RequestBody List<MetasEstrategicasRequestDTO> dtos) {
	        List<MetasEstrategicasResponseDTO> respostas = metasService.salvarLista(dtos);
	        return ResponseEntity.ok(respostas);
	    }
//	  put por id
	    @PutMapping("/{id}")
	    public ResponseEntity<MetasEstrategicasResponseDTO> atualizar(@PathVariable Long id, @RequestBody MetasEstrategicasRequestDTO dto) {
	        return ResponseEntity.ok(metasService.atualizar(id, dto));
	    }
//	    delete por id
	   	   
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletar(@PathVariable Long id) {
	        metasService.deletar(id);
	        return ResponseEntity.noContent().build();
	    }
	}
