package org.serratec.backend.gestao_competencias.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.serratec.backend.gestao_competencias.DTO.*;
import org.serratec.backend.gestao_competencias.entity.Colaborador;
import org.serratec.backend.gestao_competencias.repository.ColaboradorRepository;
import org.serratec.backend.gestao_competencias.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@Tag(name="myOpenAPI")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository repository;

    @Autowired
    private ColaboradorService service;

    @Operation(summary = "Realiza uma LISTA de colaboradores", method = "GET")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ColaboradorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }


    @Operation(summary = "Realiza a BUSCA de um colaborador, infomando seu id", method = "GET/ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ColaboradorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }


    @Operation(summary = "Realiza a INSERCAO de um colaborador" , method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ColaboradorResponseDTO> inserir ( @RequestBody ColaboradorRequestDTO dto) {
        return ResponseEntity.ok(service.inserir(dto));
    }



    @Operation(summary = "Realiza a INSERCAO de uma  lista de colaborador" , method = "POST")
    @PostMapping("/lista")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Colaborador> inserir(List<Colaborador> Colaboradores) {
        return repository.saveAll(Colaboradores);
    }


    @Operation(summary = "Realiza a ATUALIZACAO do Colaborador, informando seu ID", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ColaboradorResponseDTO> atualizar(@Valid @RequestBody ColaboradorRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }


    @Operation(summary = "Realiza a EXCLUSAO do colaborador, informando seu ID", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remover (@PathVariable Long id){
//        if(repository.existsById(id)){
//            repository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.noContent().build();
        service.remover(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/hardskills")
    public ResponseEntity<ColaboradorHardSkillResponseDTO> associarHardSkill(
            @PathVariable Long id,
            @RequestBody AssociarHardSkillRequestDTO requestDTO) {
        ColaboradorHardSkillResponseDTO dto = service.associarHardSkill(id, requestDTO);
        return ResponseEntity.ok(dto);
    }

    //  associar hard skill e seus tópicos dominados
//    @PostMapping("/{colaboradorId}/associar-hardskill")
//    public ResponseEntity<ColaboradorHardSkillResponseDTO> associarHardSkill(
//            @PathVariable Long colaboradorId,
//            @RequestBody AssociarHardSkillRequestDTO requestDTO) {
//        return ResponseEntity.ok(service.associarHardSkill(colaboradorId, requestDTO));
//    }


    @PostMapping("/{colaboradorId}/adicionar-softskills")
    public ResponseEntity<ColaboradorResponseDTO> adicionarSoftSkills(
            @PathVariable Long colaboradorId,
            @RequestBody List<Long> softSkillIds) {
        return ResponseEntity.ok(service.adicionarSoftSkills(colaboradorId, softSkillIds));
    }

}
