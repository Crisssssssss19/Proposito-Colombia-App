package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UbicacionResponseDTO;

import java.util.List;

public interface UbicacionService {
    ApiResponseDTO<List<UbicacionResponseDTO>> listarUbicaciones();
    ApiResponseDTO<List<UbicacionResponseDTO>> autocompletarUbicaciones(String texto);
}
