package com.procol.procolombia.vacante.dto;

public record AnuncioDto (
        Integer id,
        Integer idVacante,
        String nombrePublicoAnuncio,
    String nombrePrivadoAnuncio,
    String tipoAnuncio,
    String tamanioAnuncio
) {}