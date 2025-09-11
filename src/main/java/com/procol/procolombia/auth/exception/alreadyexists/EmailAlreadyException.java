package com.procol.procolombia.auth.exception.alreadyexists;

import com.procol.procolombia.auth.exception.ResourceAlreayException;

public class EmailAlreadyException extends ResourceAlreayException {
    public EmailAlreadyException(String message) {
        super(message);
    }
}
