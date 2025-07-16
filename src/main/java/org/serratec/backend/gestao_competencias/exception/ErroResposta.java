package org.serratec.backend.gestao_competencias.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErroResposta{
  private Integer StatusCode;
  private String tituloResposta;
  private LocalDateTime dataHoraResposta;
  private List<String> erros;
}
