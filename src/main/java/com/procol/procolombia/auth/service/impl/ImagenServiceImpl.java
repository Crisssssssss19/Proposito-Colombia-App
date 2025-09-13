package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.notfound.ImagenNotFoundException;
import com.procol.procolombia.auth.exception.notfound.UsuarioNotFoundException;
import com.procol.procolombia.auth.mappers.ImagenMapper;
import com.procol.procolombia.auth.repositories.ImageneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.auth.service.ImagenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImagenServiceImpl implements ImagenService {

    private final ImageneRepository imagenRepository;
    private final ImagenMapper imagenMapper;
    private final UsuarioRepository usuarioRepository;

    @Value("${procol.ruta.imagenes}")
    private String rutaImagenes;

    public ImagenServiceImpl(UsuarioRepository usuarioRepository, ImageneRepository imagenRepository, ImagenMapper imagenMapper) {
        this.imagenRepository = imagenRepository;
        this.imagenMapper = imagenMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ApiResponseDTO<ImagenResponseDTO> agregarImagen(Integer idUsuario, MultipartFile archivo, Short favoritaImagen) {
        try {
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

            String nombrePublico = archivo.getOriginalFilename();
            String tipo = archivo.getContentType();
            String tamanio = archivo.getSize() / 1024 + "KB";

            Path destino = Paths.get(rutaImagenes, nombrePublico);
            Files.copy(archivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            String base64 = Base64.getEncoder().encodeToString(archivo.getBytes());

            Imagene imagen = new Imagene();
            imagen.setIdUsuario(usuario);
            imagen.setNombrePublicoImagen(nombrePublico);
            imagen.setNombrePrivadoImagen(base64);
            imagen.setTamanioImagen(tipo);
            imagen.setTamanioImagen(tamanio);
            imagen.setTipoImagen(favoritaImagen.toString());

            Imagene guardada = imagenRepository.save(imagen);
            ImagenResponseDTO dto = imagenMapper.toDto(guardada);
            return new ApiResponseDTO<>(201, "Imagen agregada correctamente", dto, LocalDateTime.now().toString());
        } catch (IOException e) {
            throw new RuntimeException("Error al agregar imagen", e);
        }
    }

    @Override
    public ApiResponseDTO<ImagenResponseDTO> imagenPerfil(Integer idUsuario) {
        Imagene favorita = imagenRepository.findByIdUsuario_IdAndFavoritaImagen(idUsuario, (short) 1)
                .orElseThrow(() -> new RuntimeException("Usuario no tiene imagen favorita"));
        ImagenResponseDTO imagenesDTO = imagenMapper.toDto(favorita);
        return new ApiResponseDTO<>(200, "Imagen de perfil obtenida", imagenesDTO, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<String> eliminarImagen(Integer idImagen) {
        Imagene imagen = imagenRepository.findById(idImagen)
                .orElseThrow(() -> new ImagenNotFoundException("Imagen no encontrado"));

        imagenRepository.delete(imagen);
        return new ApiResponseDTO<>(200, "Imagen eliminada correctamente", "OK", LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<ImagenResponseDTO> marcarFavorita(Integer idImagen, Integer idUsuario) {
        List<Imagene> imagenes = imagenRepository.findByIdUsuario_Id(idUsuario);

        for(Imagene imagen : imagenes) {
            imagen.setFavoritaImagen((short) 2);
        }

        Imagene favorita = imagenRepository.findById(idImagen)
                .orElseThrow(() -> new ImagenNotFoundException("Imagen no encontrado"));
        favorita.setFavoritaImagen((short) 1);
        imagenRepository.saveAll(imagenes);

        ImagenResponseDTO dto = imagenMapper.toDto(favorita);
        return new ApiResponseDTO<>(200, "Imagen marcda como favorita", dto, LocalDateTime.now().toString());
    }
}
