package com.procol.procolombia.auth.exception.notfound;

import com.procol.procolombia.auth.exception.ResourceNotFoundException;

public class AccesoNotFoundException extends ResourceNotFoundException {
  public AccesoNotFoundException(String message) {
    super(message);
  }
}
