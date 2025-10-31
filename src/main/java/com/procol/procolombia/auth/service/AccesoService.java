package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Request.*;
import com.procol.procolombia.auth.dto.Response.*;

import java.util.List;

public interface AccesoService {
    ApiResponseDTO<String> eliminarAcceso(Integer idAcceso);
    ApiResponseDTO<String> cambiarClave(Integer idUsuario, CambiarClaveRequestDTO requestDTO);
    ApiResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);
    ApiResponseDTO<UserRegisterResponseDTO> register(UserRegisterRequestDTO userRegisterRequestDTO);
    ApiResponseDTO<AccesoResponseDTO> editarAcceso(Integer idAcceso, AccesoRequestDTO accesoRequestDTO);
    ApiResponseDTO<List<AccesoResponseDTO>> ListarAcceso();
    ApiResponseDTO<AccesoResponseDTO> crearAcceso(AccesoRequestDTO requestDTO);
    ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorId(Integer idAcceso);
    ApiResponseDTO<String> enviarVerificarCorreo(String correo);
    ApiResponseDTO<String> verificarCorreo(Integer idUsuario, String UUID);
}
