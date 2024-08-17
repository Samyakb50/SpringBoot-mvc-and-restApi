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
    public ResponseEntity<ApiError> handleEmployeeNotFound(ResourceNotFoundException exception){

//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND).message("Employee not found").build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){

//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Input Validation failure").subErrors(errors).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
