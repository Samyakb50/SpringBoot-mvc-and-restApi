package com.study.springbootwebtutorial.advices;

import lombok.Data;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import java.util.List;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;

    List<String> subErrors;
}
