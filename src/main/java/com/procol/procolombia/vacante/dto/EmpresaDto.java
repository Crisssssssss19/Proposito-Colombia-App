package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.Interes;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import java.util.Set;

public record EmpresaDto(
    Integer id,
    Integer idTipoEmpresa,
    String nombreEmpresa,
    String direccionEmpresa,
    String telefonoEmpresa,
    Set<Interes> interes,
    Set<RelUsuarioEmpresa> relUsuarioEmpresa
){}