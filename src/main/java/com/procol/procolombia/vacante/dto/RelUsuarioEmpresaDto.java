package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;

import java.util.Set;

public record RelUsuarioEmpresaDto(
    RelUsuarioEmpresaId id,
    Integer idUsuario,
    Integer idEmpresa,
    Short permisoRelUsuarioEmpresa,
    Set<Integer> vacanteIds
){}