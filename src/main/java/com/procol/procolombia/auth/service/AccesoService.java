package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;

import java.util.List;

public interface AccesoService {
    ApiResponseDTO<String> eliminarAcceso(Integer idUsuario);
    ApiResponseDTO<AccesoResponseDTO> editarAcceso(Integer idUsuario, UsuarioRequestDTO requestDTO);
    ApiResponseDTO<List<AccesoResponseDTO>> ListarAcceso(Integer idUsuario);
    ApiResponseDTO<AccesoResponseDTO> crearAcceso(UsuarioRequestDTO requestDTO);
    ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorId(Integer idUsuario);
}
