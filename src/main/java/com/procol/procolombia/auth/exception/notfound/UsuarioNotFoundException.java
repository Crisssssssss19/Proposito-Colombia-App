package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class UsuarioNotFoundException extends ResourceNotFoundException {
    public UsuarioNotFoundException(String message) { super(message); }
}
