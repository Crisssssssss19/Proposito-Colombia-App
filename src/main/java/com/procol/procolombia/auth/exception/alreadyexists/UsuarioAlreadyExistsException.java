package com.procol.procolombia.auth.exception.alreadyexists;

import com.procol.procolombia.auth.exception.ResourceAlreayException;

public class UsuarioAlreadyExistsException extends ResourceAlreayException {
    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }
}
