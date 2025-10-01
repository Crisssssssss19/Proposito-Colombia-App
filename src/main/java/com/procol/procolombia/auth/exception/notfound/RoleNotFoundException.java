package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class RoleNotFoundException extends ResourceNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
