package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.GetUsuario;
import com.procol.procolombia.perfil.mappers.UsuarioMapper;
import com.procol.procolombia.perfil.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    private UbicacioneRepository ubicacioneRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UbicacioneRepository ubicacioneRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.ubicacioneRepository = ubicacioneRepository;
    }

    @Override
    public GetUsuario crearUsuario(SaveUsuario saveUsuario) {
        Usuario usuario = usuarioMapper.saveUsuarioToUsuario(saveUsuario);
        if (saveUsuario.idUbicacion() != null) {
            usuario.setIdUbicacion(obtenerUbicacionPorId(saveUsuario.idUbicacion()));
        }
        Usuario usarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToGetUsuario(usarioGuardado);
    }

    @Override
    public GetUsuario actualizarUsuario(Integer id, SaveUsuario saveUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setTipoDocumentoUsuario(saveUsuario.tipoDocumento());
        usuario.setEstadoUsuario(saveUsuario.estado());
        if (saveUsuario.idUbicacion() != null) {
            usuario.setIdUbicacion(obtenerUbicacionPorId(saveUsuario.idUbicacion()));
        }
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToGetUsuario(usuarioActualizado);
    }

    @Override
    public GetUsuario obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return usuarioMapper.usuarioToGetUsuario(usuario);
    }

    @Override
    public List<GetUsuario> listarUsuarios() {
        return usuarioMapper.usuarioListToGetUsuarioList(usuarioRepository.findAll());
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con id " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private Ubicacione obtenerUbicacionPorId(Integer id) {
        return ubicacioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ubicación no encontrada"));
    }
}
