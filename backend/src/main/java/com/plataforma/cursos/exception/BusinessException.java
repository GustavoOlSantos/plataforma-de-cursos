package com.plataforma.cursos.exception;

public class BusinessException extends RuntimeException {
    private final boolean exposeMessage;

    public BusinessException(String message) {
        this(message, false);
    }

    public BusinessException(String message, boolean exposeMessage) {
        super(message);
        this.exposeMessage = exposeMessage;
    }

    public boolean isExposeMessage() {
        return exposeMessage;
    }
}