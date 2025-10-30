package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.CorreoVerificacionResponseDTO;
import com.procol.procolombia.auth.entities.CorreoVerificacion;
import org.springframework.stereotype.Service;

public interface CorreoVerificacionService {
    ApiResponseDTO<String> enviarVerificarCorreo(String correoVerificacion);
    ApiResponseDTO<String> verificarCorreo(String correoVerificacion, String codigo);
    ApiResponseDTO<CorreoVerificacionResponseDTO> obtenerPorCorreo(String correoVerificacion);
}
