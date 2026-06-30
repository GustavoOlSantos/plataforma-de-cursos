package com.plataforma.cursos.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final boolean exposeMessage;
    private final HttpStatus status;
    private final String operacao;

    public BusinessException(String message) {
        this(message, false, HttpStatus.BAD_REQUEST, "Business Exception");
    }

    public BusinessException(String message, boolean exposeMessage, HttpStatus status, String operacao) {
        super(message);

        this.exposeMessage = exposeMessage;
        this.status = status;
        this.operacao = operacao;
    }
}