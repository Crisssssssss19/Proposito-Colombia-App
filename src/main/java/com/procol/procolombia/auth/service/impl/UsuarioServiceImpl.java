package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.notfound.UsuarioNotFoundException;
import com.procol.procolombia.auth.mappers.UsuarioMapper;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.auth.service.UsuarioService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final EntityManager entityManager;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, EntityManager entityManager, UbicacioneRepository ubicacioneRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.entityManager = entityManager;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public ApiResponseDTO<UsuarioResponseDTO> obtenerUsuarioPorId(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
        return new ApiResponseDTO<>(200, "Usuario encontrado", usuarioMapper.toDto(usuario), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<UsuarioResponseDTO>> obtenerUsuarioPorNombre(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findByNombresUsuario(nombre);
        List<UsuarioResponseDTO> usuariosDTO = usuarios.stream().map(usuarioMapper::toDto).toList();

        return new ApiResponseDTO<>(200, "Usuarios encontrados :" + usuariosDTO.size(), usuariosDTO, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<UsuarioResponseDTO>> obtenerUsuarioPorTipoDocumento(Short tipo) {
        List<Usuario> usuarios = usuarioRepository.findByTipoDocumentoUsuario(tipo);
        List<UsuarioResponseDTO> usuariosDTO = usuarios.stream().map(usuarioMapper::toDto).toList();

        return new ApiResponseDTO<>(200, "Usuarios encontrados :" + usuariosDTO.size(), usuariosDTO, LocalDateTime.now().toString());
    }

    @Transactional
    @Override
    public ApiResponseDTO<UsuarioResponseDTO> crearUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        Usuario guardado = usuarioRepository.saveAndFlush(usuario);
        Usuario usuarioUbi = usuarioRepository.findByIdWithUbicacion(usuario.getId())
                .orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario recien creado"));
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toDto(usuarioUbi);
        return new ApiResponseDTO<>(201, "Usuario creado exitosamente", usuarioResponseDTO, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> listaUsuarios = usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .toList();

        return new ApiResponseDTO<>(200, "Lista de usuarios: "+listaUsuarios.size(), listaUsuarios, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<UsuarioResponseDTO> editarUsuario(Integer idUsuario, UsuarioRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con id:" + idUsuario));

        usuario.setNombresUsuario(requestDTO.nombresUsuario());
        usuario.setApellidosUsuario(requestDTO.apellidosUsuario());
        usuario.setEstadoUsuario(requestDTO.estadoUsuario());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new ApiResponseDTO<>(200, "Usuario actualizado con exito", usuarioMapper.toDto(usuarioGuardado), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<String> eliminarUsuario(Integer idUsuario) {
        if(!usuarioRepository.existsById(idUsuario)) {
            throw new UsuarioNotFoundException("Usuario no encontrado con id: "+ idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
        return new ApiResponseDTO<>(200, "Usuario eliminado con exito", null, LocalDateTime.now().toString());
    }
}
