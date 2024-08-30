package com.web.coffee_api.services.exceptions;

import java.io.Serial;

public class IlegalOperation extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public IlegalOperation(String message) {
        super(message);
    }
}
