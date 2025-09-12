package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.notfound.UsuarioNotFoundException;
import com.procol.procolombia.auth.mappers.ImagenMapper;
import com.procol.procolombia.auth.repositories.ImageneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.auth.service.ImagenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class ImagenServiceImpl implements ImagenService {

    private final ImageneRepository imagenRepository;
    private final ImagenMapper imagenMapper;
    private final UsuarioRepository usuarioRepository;
    public ImagenServiceImpl(UsuarioRepository usuarioRepository, ImageneRepository imagenRepository, ImagenMapper imagenMapper) {
        this.imagenRepository = imagenRepository;
        this.imagenMapper = imagenMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ApiResponseDTO<ImagenResponseDTO> agregarImagen(Integer idUsuario, MultipartFile imagen, short favoritaImagen) {
        try {
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

            //convertir a base64
            String base64 = Base64.getEncoder().encodeToString(imagen.getBytes());
            String nombrePublico = imagen.getOriginalFilename();
            String tipo = imagen.getContentType();
            String tamanio = imagen.getSize() / 1024 + "KB";

            Imagene imagene = new Imagene();
            imagene.setId(idUsuario);
            imagene.setNombrePublicoImagen(nombrePublico);
            imagene.setNombrePrivadoImagen(base64);
            imagene.setTipoImagen(tipo);
            imagene.setTamanioImagen(tamanio);
            imagene.setFavoritaImagen(favoritaImagen);

            if(favoritaImagen == 1){
                List<Imagene> todas = imagenRepository.findByIdUsuario_Id(idUsuario);
                todas.forEach(img -> img.setFavoritaImagen((short) 2));
                imagenRepository.saveAll(todas);
            }

            Imagene guardada = imagenRepository.save(imagene);
            ImagenResponseDTO dto = imagenMapper.toDto(guardada);

            return new ApiResponseDTO<>(201, "Imagen agregada correctamente", dto, LocalDateTime.now().toString());
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar archivo", e);
        }
    }

    @Override
    public ApiResponseDTO<Void> eliminarImagen(Integer idImagen) {
        return null;
    }

    @Override
    public ApiResponseDTO<ImagenResponseDTO> marcarFavorita(Integer idImagen) {
        return null;
    }

    @Override
    public ApiResponseDTO<Void> listarPorUsuario(Integer idUsuario) {
        return null;
    }

    @Override
    public ImagenResponseDTO getFavoritaPorUsuario(Integer idUsuario) {
        return null;
    }
}
