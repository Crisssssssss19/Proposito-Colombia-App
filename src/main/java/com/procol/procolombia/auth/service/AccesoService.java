package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;

import java.util.List;

public interface AccesoService {
    ApiResponseDTO<String> eliminarAcceso(Integer idAcceso);
    ApiResponseDTO<String> cambiarClave(Integer idUsuario, String clave);
    ApiResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);
    ApiResponseDTO<AccesoResponseDTO> editarAcceso(Integer idAcceso, AccesoRequestDTO accesoRequestDTO);
    ApiResponseDTO<List<AccesoResponseDTO>> ListarAcceso();
    ApiResponseDTO<AccesoResponseDTO> crearAcceso(AccesoRequestDTO requestDTO);
    ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorId(Integer idAcceso);
}
