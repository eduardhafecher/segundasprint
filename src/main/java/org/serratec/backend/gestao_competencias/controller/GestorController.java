package org.serratec.backend.gestao_competencias.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.backend.gestao_competencias.DTO.GestorDTO;
import org.serratec.backend.gestao_competencias.DTO.GestorRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.GestorResponseDTO;
import org.serratec.backend.gestao_competencias.repository.GestorRepository;
import org.serratec.backend.gestao_competencias.service.GestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gestores")
@Tag(name="myOpenAPI")
public class GestorController {

    @Autowired
    private GestorService service;

    @Operation(summary = "Realiza a LISTAGEM dos gestores", method = "GET")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GestorResponseDTO>> listarGestores() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Realiza a BUSCA de uma gestor pelo ID", method = "GET/ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GestorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Realiza O ENVIO de um gestor", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GestorResponseDTO> inserir(@Valid @RequestBody GestorRequestDTO dto) {
        return ResponseEntity.ok(service.inserir(dto));
    }

    @Operation(summary = "Realiza a ALTERAÇÃO de uma gestor pelo ID", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GestorDTO> atualizar(@Valid @RequestBody GestorDTO GestorDTO, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizar(id, GestorDTO));
    }

    @Operation(summary = "Realiza a EXCLUSAO de uma gestor pelo ID", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
