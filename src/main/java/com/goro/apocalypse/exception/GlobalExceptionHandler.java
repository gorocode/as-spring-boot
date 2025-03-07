package com.goro.apocalypse.exception;

import com.goro.apocalypse.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFound(EntityNotFoundException ex) {
        ResponseDTO errorResponse = new ResponseDTO();
        errorResponse.setMessageEn(ex.getMessageEn());
        errorResponse.setMessageEs(ex.getMessageEs());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFound(AuthorizationDeniedException ex) {
        ResponseDTO errorResponse = new ResponseDTO();
        errorResponse.setMessageEn(ex.getMessageEn());
        errorResponse.setMessageEs(ex.getMessageEs());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception ex) {
        ResponseDTO errorResponse = new ResponseDTO();
        errorResponse.setMessageEn("An unexpected error occurred!");
        errorResponse.setMessageEs("¡Ocurrió un error inesperado!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
