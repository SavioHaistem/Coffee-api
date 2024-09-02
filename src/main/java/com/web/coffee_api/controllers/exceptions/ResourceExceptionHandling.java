package com.web.coffee_api.controllers.exceptions;

import com.web.coffee_api.services.exceptions.ArgumentsException;
import com.web.coffee_api.services.exceptions.IllegalOperation;
import com.web.coffee_api.services.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandling {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFound exception, HttpServletRequest request) {
        Instant timestamp = Instant.now();
        String error = "resource not found";
        String message = exception.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        StandardError resourceNotFoundException = new StandardError(timestamp,status.value(),error,message,path);
        return ResponseEntity.status(status).body(resourceNotFoundException);
    }

    @ExceptionHandler(IllegalOperation.class)
    public ResponseEntity<SimpleError> illegalOperation(IllegalOperation exception, HttpServletRequest request) {
        Instant instant = Instant.now();
        String error = "Illegal operation";
        String server_message = exception.getServer_message();
        String message = "refused operation: " + exception.getMessage();
        String path = request.getRequestURI();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        SimpleError illegalOperationException = new SimpleError(instant,error,server_message,message,path,status.value());
        return ResponseEntity.status(status).body(illegalOperationException);
    }

    @ExceptionHandler(ArgumentsException.class)
    public ResponseEntity<SimpleError> argumentsException(ArgumentsException exception,HttpServletRequest request) {
        Instant instant = Instant.now();
        String error = "Arguments exception";
        String server_message = exception.getServer_message();
        String message = "refused operation: " + exception.getMessage();
        String path = request.getRequestURI();
        SimpleError argumentsException = new SimpleError(
                instant,
                error,
                server_message,
                message,
                path,
                exception.getStatus().value()
        );

        return ResponseEntity.status(exception.getStatus()).body(argumentsException);
    }
}
