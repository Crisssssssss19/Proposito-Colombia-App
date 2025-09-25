package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;

public interface PreRegistroService {
    ApiResponseDTO<String> enviarCodigo(String telefono);
    ApiResponseDTO<String> validarCodigo(String telefono, String codigo);
}
