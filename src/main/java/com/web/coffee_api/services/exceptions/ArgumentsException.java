package com.web.coffee_api.services.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ArgumentsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String server_message;
    private HttpStatus status;

    public ArgumentsException(String server_message,String message,HttpStatus status) {
        super(message);
        this.server_message = server_message;
        this.status = status;
    }

    public String getServer_message() {
        return server_message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setServer_message(String server_message) {
        this.server_message = server_message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
