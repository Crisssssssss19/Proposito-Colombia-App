package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagenService {
    ApiResponseDTO<ImagenResponseDTO> agregarImagen(Integer idUsuario, MultipartFile archivo, Short favoritaImagen);
    ApiResponseDTO<ImagenResponseDTO> imagenPerfil(Integer idUsuario);
    ApiResponseDTO<String> eliminarImagen(Integer idImagen);
    ApiResponseDTO<ImagenResponseDTO> marcarFavorita(Integer idImagen, Integer idUsuario);
}

