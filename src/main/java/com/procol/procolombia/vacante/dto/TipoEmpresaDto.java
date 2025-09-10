package com.procol.procolombia.vacante.dto;


import java.util.Set;

public record TipoEmpresaDto (
    Integer id,
    String nombreTipoEmpresa,
    Short estadoTipoEmpresa,
    Set<Integer> empresaIds
){}