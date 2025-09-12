package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Imagen;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.ImagenRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import com.procol.procolombia.perfil.mappers.ImagenMapper;
import com.procol.procolombia.perfil.services.ImagenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenServiceImpl implements ImagenService {
    private final ImagenRepository  imagenRepository;
    private final UsuarioRepository usuarioRepository;
    private final ImagenMapper  imagenMapper;

    public ImagenServiceImpl(ImagenRepository imagenRepository, UsuarioRepository usuarioRepository, ImagenMapper imagenMapper) {
        this.imagenRepository = imagenRepository;
        this.usuarioRepository = usuarioRepository;
        this.imagenMapper = imagenMapper;
    }

    @Override
    public GetImagen crearImagen(SaveImagen saveImagen) {
        Usuario usuario = usuarioRepository.findById(saveImagen.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Imagen imagen = imagenMapper.saveImagenToImagen(saveImagen);
        imagen.setIdUsuario(usuario);
        return imagenMapper.imagenToGetImagen(imagenRepository.save(imagen));
    }

    @Override
    public List<GetImagen> listarImagenesPorUsuario(Integer idUsuario){
        return imagenMapper.imagenListToGetImagenList(imagenRepository.findByIdUsuario_Id(idUsuario));
    }

    @Override
    public void eliminarImagen(Integer idImagen) {
        imagenRepository.deleteById(idImagen);
    }
}
