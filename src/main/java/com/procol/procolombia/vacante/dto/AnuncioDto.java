package com.procol.procolombia.vacante.dto;

public record AnuncioDto (
        Integer id,
        Integer vacanteId,
        String NombrePublicoAnuncio,
    String nombrePrivadoAnuncio,
    String tipoAnuncio,
    String tamanioAnuncio
) {}