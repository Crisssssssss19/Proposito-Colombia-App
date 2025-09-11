package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;
import com.procol.procolombia.auth.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    ApiResponseDTO<UsuarioResponseDTO> obtenerUsuarioPorId(Integer idUsuario);
    ApiResponseDTO<List<UsuarioResponseDTO>> obtenerUsuarioPorNombre(String nombre);
    ApiResponseDTO<List<UsuarioResponseDTO>> obtenerUsuarioPorTipo(String tipo);
    ApiResponseDTO<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO usuario);
    ApiResponseDTO<List<UsuarioResponseDTO>> listarUsuarios();
    ApiResponseDTO<UsuarioResponseDTO> editarUsuario(Integer idUsuario, UsuarioRequestDTO requestDTO);
    ApiResponseDTO<String> eliminarUsuario(Integer idUsuario);
}
