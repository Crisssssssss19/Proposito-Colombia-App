package com.procol.procolombia.auth.dto.Response;

import com.procol.procolombia.auth.entities.Usuario;

public record ImagenResponseDTO(
        Integer idImagen,
        Usuario idUsuario,
        String nombrePublicoImagen,
        String tipoImagen,
        String tamanioImagen,
        Short favoritaImagen
) {}
