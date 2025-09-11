package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;
import com.procol.procolombia.auth.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioRequestDTO usuarioResponseDTO);
}
