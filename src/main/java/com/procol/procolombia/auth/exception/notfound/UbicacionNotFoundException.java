package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class UbicacionNotFoundException extends ResourceNotFoundException {
    public UbicacionNotFoundException(String message) {
        super(message);
    }
}
