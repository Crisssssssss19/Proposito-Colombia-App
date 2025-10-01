package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class RolNotFoundException extends ResourceNotFoundException {
    public RolNotFoundException(String message) {
        super(message);
    }
}
