package com.plataforma.cursos.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.exception.ErrorResponse;

@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex) {
        String responseMessage = ex.isExposeMessage()
                ? ex.getMessage()
                : "Não foi possível completar a operação";

        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(responseMessage));
    }
}