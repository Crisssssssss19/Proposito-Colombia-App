package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveImagenFile;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagenConUrl;

import java.nio.file.Path;
import java.util.List;

public interface ImagenService {
    GetImagenConUrl SubirImagen(Integer idUsuario, SaveImagenFile saveImagen);
    List<GetImagenConUrl> listarImagenesPorUsuario(Integer idUsuario);
    void eliminarImagen(Integer idImagen);
    GetImagenConUrl marcarComoFavorita(Integer idImagen);
    Path obtenerRutaImagen(String nombreArchivo);
}
