package com.plataforma.cursos.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.plataforma.cursos.exception.BusinessException;
import com.plataforma.cursos.exception.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Não foi possível completar a operação"));
    }
}