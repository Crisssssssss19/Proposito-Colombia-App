package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {
    ApiResponseDTO<ImagenResponseDTO> agregarImagen(Integer idUsuario, MultipartFile imagen, short favoritaImagen);
    ApiResponseDTO<Void> eliminarImagen(Integer idImagen);
    ApiResponseDTO<ImagenResponseDTO> marcarFavorita(Integer idImagen);
    ApiResponseDTO<Void> listarPorUsuario(Integer idUsuario);
    ImagenResponseDTO getFavoritaPorUsuario(Integer idUsuario);
}
