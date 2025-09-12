package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.auth.entities.Usuario;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccesoMapper {

    @Mapping(source = "usuario.id", target = "usuario")
    AccesoResponseDTO toDto(Acceso acceso);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "id", ignore = true)
    Acceso toEntity(AccesoRequestDTO accesoRequestDTO);

    @AfterMapping
    default void setUsuario(@MappingTarget Acceso acceso, AccesoRequestDTO accesoRequestDTO) {
        if (accesoRequestDTO.usuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(accesoRequestDTO.usuario());
            acceso.setUsuario(usuario);
            // El id se establecerá automáticamente por @MapsId cuando la entidad sea persistida
            // No establezcas acceso.id explícitamente aquí
        }
    }
}