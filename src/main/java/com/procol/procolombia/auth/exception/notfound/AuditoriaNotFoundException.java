package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class AuditoriaNotFoundException extends ResourceNotFoundException {
    public AuditoriaNotFoundException(String message) {
        super(message);
    }
}
