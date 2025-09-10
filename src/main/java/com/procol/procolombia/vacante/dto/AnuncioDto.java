package com.procol.procolombia.vacante.dto;

public record AnuncioDto (
    Integer id,
    Integer vacantesId,
    String nombrePublicoAnuncio,
    String nombrePrivadoAnuncio,
    String tipoAnuncio,
    String tamanioAnuncio
) {}