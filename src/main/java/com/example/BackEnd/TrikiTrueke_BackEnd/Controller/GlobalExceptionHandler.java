package com.Example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Object>> handleResponseStatusException(
            ResponseStatusException ex,
            HttpServletRequest request
    ) {
        int status = ex.getStatusCode().value();
        String message = ex.getReason();
        if (message == null || message.isBlank()) {
            message = "Error inesperado";
        }

        ApiResponse<Object> body = new ApiResponse<>(
                false,
                status,
                message,
                null,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(HttpServletRequest request) {
        ApiResponse<Object> body = new ApiResponse<>(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                null,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
