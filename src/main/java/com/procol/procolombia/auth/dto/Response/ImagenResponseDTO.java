package com.procol.procolombia.auth.dto.Response;

public record ImagenResponseDTO(
        Integer idImagen,
        Integer idUsuario,
        String nombrePublicoImagen,
        String tipoImagen,
        String tamanioImagen,
        Short favoritaImagen
) {}
