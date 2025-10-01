package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class PreRegistroNotFountException extends ResourceNotFoundException {
    public PreRegistroNotFountException(String message) {
        super(message);
    }
}
