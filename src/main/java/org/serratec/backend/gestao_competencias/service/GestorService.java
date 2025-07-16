package org.serratec.backend.gestao_competencias.service;
import org.serratec.backend.gestao_competencias.DTO.GestorDTO;
import org.serratec.backend.gestao_competencias.DTO.GestorRequestDTO;
import org.serratec.backend.gestao_competencias.DTO.GestorResponseDTO;
import org.serratec.backend.gestao_competencias.entity.Gestor;
import org.serratec.backend.gestao_competencias.exception.GestorException;
import org.serratec.backend.gestao_competencias.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;


    public List<GestorResponseDTO> listarTodos() {
        return gestorRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public GestorResponseDTO buscarPorId(Long id) {
        Gestor gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new GestorException("Gestor nao encontrado"));
        return toResponseDTO(gestor);
    }


    public GestorResponseDTO inserir(GestorRequestDTO dto) {
        return toResponseDTO(gestorRepository.save(toEntity(dto)));
    }

    public GestorDTO atualizar(Long id, GestorDTO dto) {
       Gestor gestor = gestorRepository.findById(id)
               .orElseThrow(()
               -> new GestorException("Gestor nao encontrado"));
        gestor.setNome(gestor.getNome());
        gestor.setOcupacao(gestor.getOcupacao());
        gestor.setEmail(gestor.getEmail());
        return new GestorDTO(gestorRepository.save(gestor));
    }

    public void deletar(Long id) {
        Gestor gestor = gestorRepository.findById(id).
                orElseThrow(()->
                new GestorException("Gestor nao encontrado"));
        gestorRepository.delete(gestor);
    }

    private GestorDTO toDTO(Gestor gestor) {
        return new GestorDTO(gestor);
    }

    private Gestor toEntity(GestorRequestDTO dto) {
        Gestor gestor = new Gestor();
        gestor.setNome(dto.getNome());
        gestor.setCpf(dto.getCpf());
        gestor.setOcupacao(dto.getOcupacao());
        gestor.setPassword(dto.getPassword());
        gestor.setEmail(dto.getEmail());
        return gestor;
    }

    private GestorResponseDTO toResponseDTO(Gestor gestor) {
        return new GestorResponseDTO(
                gestor.getId(),
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getOcupacao()
        );
    }
}
