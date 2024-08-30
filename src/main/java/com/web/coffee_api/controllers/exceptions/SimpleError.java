package com.web.coffee_api.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class SimpleError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonFormat(timezone = "GMT", shape = JsonFormat.Shape.STRING, pattern = "'T'HH:mm:ss'Z'")
    private Instant time;
    private String error;
    private String server_message;
    private String debug;
    private String path;
    private Integer status;

    public SimpleError() {
    }

    public SimpleError(Instant time, String error, String server_message, String message, String path, Integer status) {
        this.time = time;
        this.error = error;
        this.server_message = server_message;
        this.debug = message;
        this.path = path;
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getServer_message() {
        return server_message;
    }

    public void setServer_message(String server_message) {
        this.server_message = server_message;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
