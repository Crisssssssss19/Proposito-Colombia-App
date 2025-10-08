package com.procol.procolombia.vacante.dto;

import java.util.Set;

public record PalabrasClaveDto (
    Integer id,
    String textoPalabraClave,
    Set<Integer> usuarioIds,
    Set<Integer> vacanteIds
){}