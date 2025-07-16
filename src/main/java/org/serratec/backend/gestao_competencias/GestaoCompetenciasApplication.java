package org.serratec.backend.gestao_competencias;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "GestaoCompetenciasApplication", version = "1", description = "Api desenvolvida para tratar dados de " +
		"cadastro de usuario"))
public class GestaoCompetenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoCompetenciasApplication.class, args);
	}

}
