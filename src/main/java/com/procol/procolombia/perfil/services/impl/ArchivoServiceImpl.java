package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveArchivoFile;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import com.procol.procolombia.perfil.mappers.ArchivoMapper;
import com.procol.procolombia.perfil.services.ArchivoService;
import com.procol.procolombia.postulacion.entities.Archivo;
import com.procol.procolombia.postulacion.repositories.ArchivoRepository;
import java.io.IOException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ArchivoServiceImpl implements ArchivoService {
    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArchivoMapper archivoMapper;

    @Value("${app.upload.archivos}")
    private String uploadDir;

    public ArchivoServiceImpl(ArchivoRepository archivoRepository, UsuarioRepository usuarioRepository, ArchivoMapper archivoMapper) {
        this.archivoRepository = archivoRepository;
        this.usuarioRepository = usuarioRepository;
        this.archivoMapper = archivoMapper;
    }

    @Override
    @Transactional
    public GetArchivo SubirArchivo(Integer idUsuario, SaveArchivoFile saveArchivo) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        MultipartFile file = saveArchivo.file();
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
            throw new RuntimeException("No se pudo crear el directorio de archivos", e);
        }

        Path destino = directorio.resolve(nombrePrivado);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo guardado en: " + destino.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }

        Archivo archivo = new Archivo();
        archivo.setIdUsuario(usuario);
        archivo.setNombrePublicoArchivo(originalFilename);
        archivo.setNombreArchivoArchivo(nombrePrivado);
        archivo.setTipoArchivo(file.getContentType());
        archivo.setTamanioArchivo(file.getSize() / 1024 + " KB");
        archivo.setGrupoArchivo((short) 1);

        Archivo archivoGuardado = archivoRepository.save(archivo);

        String url = "http://localhost:3210/uploads/archivos/" + archivoGuardado.getNombreArchivoArchivo();

        return archivoMapper.archivoToGetArchivo(archivoGuardado);
    }

    @Override
    public List<GetArchivo> listarArchivosPorUsuario(Integer idUsuario) {
        return archivoMapper.archivosToGetArchivoList(archivoRepository.findByIdUsuario_Id(idUsuario));
    }

    @Override
    public void eliminarArchivo(Integer idArchivo) {
        if (!archivoRepository.existsById(idArchivo)) {
            throw new EntityNotFoundException("Archivo no encontrado");
        }
        archivoRepository.deleteById(idArchivo);
    }
}
