package com.gaven.prembbtraining.util;

import com.gaven.prembbtraining.model.exception.NoSuchUsernameException;
import com.gaven.prembbtraining.model.exception.ProfileConstraintViolationExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NoSuchUsernameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NoSuchUsernameException.ResponseBody handleUsernameNotFound(NoSuchUsernameException ex) {
        return NoSuchUsernameException.ResponseBody.builder()
                .errorMessage(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ProfileConstraintViolationExceptionResponse handleInvalidArgument(ConstraintViolationException exception) {
        var problemFields = exception.getConstraintViolations().stream().map(fieldError -> ProfileConstraintViolationExceptionResponse.ProblemFields.builder()
                        .field(fieldError.getPropertyPath().toString())
                        .reason(fieldError.getMessageTemplate())
                        .build())
                .toList();

        return ProfileConstraintViolationExceptionResponse.builder()
                .description("Bad Request")
                .problemFields(problemFields)
                .build();
    }
}