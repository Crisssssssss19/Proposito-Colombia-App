package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveArchivo;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import com.procol.procolombia.perfil.mappers.ArchivoMapper;
import com.procol.procolombia.perfil.services.ArchivoService;
import com.procol.procolombia.postulacion.entities.Archivo;
import com.procol.procolombia.postulacion.repositories.ArchivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoServiceImpl implements ArchivoService {
    private final ArchivoRepository archivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArchivoMapper archivoMapper;

    public ArchivoServiceImpl(ArchivoRepository archivoRepository, UsuarioRepository usuarioRepository, ArchivoMapper archivoMapper) {
        this.archivoRepository = archivoRepository;
        this.usuarioRepository = usuarioRepository;
        this.archivoMapper = archivoMapper;
    }

    @Override
    public GetArchivo crearArchivo(SaveArchivo saveArchivo) {
        Usuario usuario = usuarioRepository.findById(saveArchivo.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Archivo archivo = archivoMapper.saveArchivoToArchivo(saveArchivo);
        archivo.setIdUsuario(usuario);
        return  archivoMapper.archivoToGetArchivo(archivoRepository.save(archivo));
    }

    @Override
    public List<GetArchivo> listarArchivos(Integer idUsuario) {
        return archivoMapper.archivosToGetArchivoList(archivoRepository.findByIdUsuario_Id(idUsuario));
    }

    @Override
    public void eliminarArchivo(Integer idArchivo) {
        archivoRepository.deleteById(idArchivo);
    }
}
