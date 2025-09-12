package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.ImagenRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveImagenFile;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import com.procol.procolombia.perfil.mappers.ImagenMapper;
import com.procol.procolombia.perfil.services.ImagenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenServiceImpl implements ImagenService {
    private final ImagenRepository  imagenRepository;
    private final UsuarioRepository usuarioRepository;
    private final ImagenMapper  imagenMapper;

    @Value("${app.upload.dir}")
    public String uploadDir;

    public ImagenServiceImpl(ImagenRepository imagenRepository, UsuarioRepository usuarioRepository, ImagenMapper imagenMapper) {
        this.imagenRepository = imagenRepository;
        this.usuarioRepository = usuarioRepository;
        this.imagenMapper = imagenMapper;
    }

    @Override
    public GetImagen SubirImagen(Integer idUsuario, SaveImagenFile saveImagen) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MultipartFile file = saveImagen.file();
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("El archivo está vacio");
        }

        String extension = "";
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }
        String nombrePrivado = UUID.randomUUID() + extension;

        Path directorio = Paths.get(uploadDir);
        try {
            Files.createDirectories(directorio);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de imagenes", e);
        }

        Path destino = directorio.resolve(nombrePrivado);
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Imagene guardada en: " + destino.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagene", e);
        }

        Imagene imagene = new Imagene();
        imagene.setIdUsuario(usuario);
        imagene.setNombrePublicoImagen(originalFilename);
        imagene.setNombrePrivadoImagen(nombrePrivado);
        imagene.setTipoImagen(file.getContentType());
        imagene.setTamanioImagen(file.getSize() / 1024 + " KB");
        imagene.setFavoritaImagen((short) (Boolean.TRUE.equals(saveImagen.favorita()) ? 1 : 2));
        Imagene imageneGuardada = imagenRepository.save(imagene);
        String url = "http://localhost:3210/uploads/imagenes/" + imageneGuardada.getNombrePrivadoImagen();
        return imagenMapper.imagenToGetImagen(imageneGuardada);
    }

    @Override
    public List<GetImagen> listarImagenesPorUsuario(Integer idUsuario){
        return imagenMapper.imagenListToGetImagenList(imagenRepository.findByIdUsuario_Id(idUsuario));
    }

    @Override
    public void eliminarImagen(Integer idImagen) {
        if (!imagenRepository.existsById(idImagen)) {
            throw new EntityNotFoundException("Imagene no encontrada");
        }
        imagenRepository.deleteById(idImagen);
    }

    @Override
    public GetImagen marcarComoFavorita(Integer idImagen) {
        Imagene imagene = imagenRepository.findById(idImagen)
                .orElseThrow(() -> new EntityNotFoundException("Imagene no encontrada"));


        imagenRepository.findByIdUsuario_Id(imagene.getIdUsuario().getId())
                .forEach(img ->{
                    img.setFavoritaImagen((short)2);
                    imagenRepository.save(img);
                });

        imagene.setFavoritaImagen((short)1);
        Imagene imageneActualizada = imagenRepository.save(imagene);

        return imagenMapper.imagenToGetImagen(imageneActualizada);
    }
}
