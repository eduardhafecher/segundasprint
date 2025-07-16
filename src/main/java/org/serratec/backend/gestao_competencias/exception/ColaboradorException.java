package org.serratec.backend.gestao_competencias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ColaboradorException extends RuntimeException {
    public ColaboradorException(String message) {
        super(message);
    }
}
