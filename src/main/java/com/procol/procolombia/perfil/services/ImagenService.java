package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagen;

import java.util.List;

public interface ImagenService {
    GetImagen SubirImagen(Integer idUsuario, SaveImagenFile saveImagen);
    List<GetImagen> listarImagenesPorUsuario(Integer idUsuario);
    void eliminarImagen(Integer idImagen);
    GetImagen marcarComoFavorita(Integer idImagen);
}
