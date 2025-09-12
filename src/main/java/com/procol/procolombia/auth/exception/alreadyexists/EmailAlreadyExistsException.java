package com.procol.procolombia.auth.exception.alreadyexists;

import com.procol.procolombia.auth.exception.ResourceAlreayException;

public class EmailAlreadyExistsException extends ResourceAlreayException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
