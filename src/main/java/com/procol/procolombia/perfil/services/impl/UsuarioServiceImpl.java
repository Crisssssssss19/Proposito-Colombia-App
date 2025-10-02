package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.GetUsuario;
import com.procol.procolombia.perfil.mappers.UsuarioMapper;
import com.procol.procolombia.perfil.services.PalabraClaveService;
import com.procol.procolombia.perfil.services.TalentoService;
import com.procol.procolombia.perfil.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    private final TalentoService talentoService;
    private final PalabraClaveService palabraClaveService;
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    private UbicacioneRepository ubicacioneRepository;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UbicacioneRepository ubicacioneRepository, TalentoService talentoService, PalabraClaveService palabraClaveService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.ubicacioneRepository = ubicacioneRepository;
        this.talentoService = talentoService;
        this.palabraClaveService = palabraClaveService;
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

        if (saveUsuario.idUbicacion() != null) {
            usuario.setIdUbicacion(obtenerUbicacionPorId(saveUsuario.idUbicacion()));
        }

        usuario.getTalentos().addAll(
                talentoService.asignarTalentos(saveUsuario.habilidades(), saveUsuario.competencias())
        );

        usuario.getPalabrasClaves().addAll(
                palabraClaveService.asignarPalabras(saveUsuario.palabrasClave())
        );

        if (saveUsuario.nombres() != null) {
            usuario.setNombresUsuario(saveUsuario.nombres());
        }

        if (saveUsuario.apellidos() != null) {
            usuario.setApellidosUsuario(saveUsuario.apellidos());
        }

        if (saveUsuario.tipoDocumento() != null) {
            usuario.setTipoDocumentoUsuario(saveUsuario.tipoDocumento());
        }

        if (saveUsuario.estado() != null) {
            usuario.setEstadoUsuario(saveUsuario.estado());
        }

        if (saveUsuario.documento() != null) {
            usuario.setDocumentoUsuario(saveUsuario.documento());
        }
        return usuarioMapper.usuarioToGetUsuario(usuarioRepository.save(usuario));
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
