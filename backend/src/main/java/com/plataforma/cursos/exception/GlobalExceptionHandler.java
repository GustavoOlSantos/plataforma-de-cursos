package com.plataforma.cursos.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.exception.ErrorResponse;

@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex) {
        String responseMessage = ex.isExposeMessage()
                ? ex.getMessage()
                : "Não foi possível completar a operação";

        log.warn("action={} status={} mensagem={}", ex.getOperacao(), ex.getStatus().value(), ex.getMessage());

        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(responseMessage));
    }
}