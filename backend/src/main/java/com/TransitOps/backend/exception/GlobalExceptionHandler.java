package com.TransitOps.backend.exception;


import com.TransitOps.backend.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleDriverNotFound(DriverNotFoundException ex, HttpServletRequest request){

        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("NOT FOUND")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);

    }

    @ExceptionHandler(DuplicateDriverException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateDriver(DuplicateDriverException ex, HttpServletRequest request){

        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("CONFLICT")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> validationException(MethodArgumentNotValidException ex,HttpServletRequest request){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            errors.put(field,message);
        });

        ErrorResponseDTO response=ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("VALIDATION FAILED")
                .message("Validation Error")
                .validationErrors(errors)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(MaintenanceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> maintenanceNotFound(MaintenanceNotFoundException ex, HttpServletRequest request){

        ErrorResponseDTO response= ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("NOT FOUND")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(VehicleAlreadyInMaintenanceException.class)
    public ResponseEntity<ErrorResponseDTO> vehicleAlreadyInMaintenance(VehicleAlreadyInMaintenanceException ex, HttpServletRequest request){

        ErrorResponseDTO response=ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("CONFLICT")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MaintenanceAlreadyCompletedException.class)
    public ResponseEntity<ErrorResponseDTO> alreadyCompleted(MaintenanceAlreadyCompletedException ex, HttpServletRequest request){

        ErrorResponseDTO response=ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("BAD REQUEST")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex, HttpServletRequest request){

        ErrorResponseDTO response=ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("INTERNAL SERVER ERROR")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

}
