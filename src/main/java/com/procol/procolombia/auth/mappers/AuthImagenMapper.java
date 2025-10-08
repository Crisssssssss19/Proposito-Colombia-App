package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.ImagenResponseDTO;
import com.procol.procolombia.auth.entities.Imagene;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthImagenMapper {

    @Mapping(source = "idUsuario.id", target = "idUsuario")
    ImagenResponseDTO toDto(Imagene imagene);
}
