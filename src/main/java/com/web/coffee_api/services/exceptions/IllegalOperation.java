package com.web.coffee_api.services.exceptions;

import java.io.Serial;

public class IllegalOperation extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String server_message;
    public IllegalOperation(String server_message, String error_message) {
        super(error_message);
        this.server_message = server_message;
    }

    public String getServer_message() {
        return server_message;
    }

    public void setServer_message(String server_message) {
        this.server_message = server_message;
    }
}
