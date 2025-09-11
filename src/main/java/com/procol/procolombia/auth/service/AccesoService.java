package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;

import java.util.List;

public interface AccesoService {
    ApiResponseDTO<String> eliminarAcceso(Integer idAcceso);
    ApiResponseDTO<AccesoResponseDTO> editarAcceso(Integer idAcceso, AccesoRequestDTO requestDTO);
    ApiResponseDTO<List<AccesoResponseDTO>> ListarAcceso(Integer idAcceso);
    ApiResponseDTO<AccesoResponseDTO> crearAcceso(AccesoRequestDTO requestDTO);
    ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorId(Integer idAcceso);
}
