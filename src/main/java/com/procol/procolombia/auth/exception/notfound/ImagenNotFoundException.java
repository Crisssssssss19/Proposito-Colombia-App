package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class ImagenNotFoundException extends ResourceNotFoundException {
    public ImagenNotFoundException(String message) {
        super(message);
    }
}
