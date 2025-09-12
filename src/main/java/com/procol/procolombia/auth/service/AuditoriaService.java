package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.AuditoriaResponseDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;
import com.procol.procolombia.auth.mappers.AuditoriaMapper;

import java.util.List;

public interface AuditoriaService {
    ApiResponseDTO<List<AuditoriaResponseDTO>> listarAuditorias();
    ApiResponseDTO<AuditoriaResponseDTO> obtenerAuditoriaPorId(Integer idAuditoria);
}
