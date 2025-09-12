package com.procol.procolombia.auth.dto.Response;

import com.procol.procolombia.auth.entities.Usuario;

public record ImagenResponseDTO(
        Integer idImagen,
        Integer idUsuario,
        String nombrePublicoImagen,
        String nombrePrivadoImagen,
        String tipoImagen,
        String tamanioImagen,
        Short favoritaImagen
) {}
