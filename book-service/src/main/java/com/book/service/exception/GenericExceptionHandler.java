package com.book.service.exception;

import com.book.service.bean.GenericResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        GenericResponse exceptionResponse =
                new GenericResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ResourceAlreadyExistException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceAlreadyExistException ex, WebRequest request) {
        GenericResponse exceptionResponse =
                new GenericResponse(ex.getMessage(),HttpStatus.CONFLICT.value());
        return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value=InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidBookCategoryExceptions(InvalidFormatException ex, WebRequest request) {
        GenericResponse exceptionResponse =
                new GenericResponse("Invalid Book Category. One of the values accepted for Enum class: [MOTIVATIONAL, SCIENCE, FANTACY, THRILLER, TRAVEL, ROMANCE, SELF_HELP]",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericResponse exceptionResponse =
                new GenericResponse("Invalid Book Category. One of the values accepted for Enum class: [MOTIVATIONAL, SCIENCE, FANTACY, THRILLER, TRAVEL, ROMANCE, SELF_HELP]",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        GenericResponse exceptionResponse =
                new GenericResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
