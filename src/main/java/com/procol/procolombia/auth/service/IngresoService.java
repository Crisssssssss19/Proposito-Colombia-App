package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.IngresoResponseDTO;
import com.procol.procolombia.auth.entities.Ingreso;

import java.util.List;

public interface IngresoService {
    ApiResponseDTO<IngresoResponseDTO> crearIngreso(Integer idAcceso);
    ApiResponseDTO<List<IngresoResponseDTO>> listarIngresos();
    ApiResponseDTO<IngresoResponseDTO> buscarIngresoPorId(Integer id);
    ApiResponseDTO<List<IngresoResponseDTO>> listarIngresosPorUsuario(Integer idUsuario);
}
