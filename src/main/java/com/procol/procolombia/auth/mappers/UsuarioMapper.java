package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;
import com.procol.procolombia.auth.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UbicacionMapper.class})
public interface UsuarioMapper {

    @Mapping(source = "id", target = "idUsuario")
    @Mapping(source = "idUbicacion", target = "ubicacionResponseDTO")
    UsuarioResponseDTO toDto(Usuario usuario);

    @Mapping(source = "idUbicacion", target = "idUbicacion.id")
    Usuario toEntity(UsuarioRequestDTO usuarioResponseDTO);
}
