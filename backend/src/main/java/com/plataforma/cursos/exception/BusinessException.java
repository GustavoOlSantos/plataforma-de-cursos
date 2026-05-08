package com.plataforma.cursos.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final boolean exposeMessage;
    private final HttpStatus status;

    public BusinessException(String message) {
        this(message, false, HttpStatus.BAD_REQUEST);
    }

    public BusinessException(
            String message,
            boolean exposeMessage,
            HttpStatus status
    ) {
        super(message);

        this.exposeMessage = exposeMessage;
        this.status = status;
    }

    public boolean isExposeMessage() {
        return exposeMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}