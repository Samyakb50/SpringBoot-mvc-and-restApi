package com.study.springbootwebtutorial.advices;

import com.study.springbootwebtutorial.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEmployeeNotFound(ResourceNotFoundException exception){

//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Employee not found").build();
        return buildErrorResponseEntity(apiError);
    }

    public ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){

//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Input Validation failure").subErrors(errors).build();
        return buildErrorResponseEntity(apiError);
    }
}
