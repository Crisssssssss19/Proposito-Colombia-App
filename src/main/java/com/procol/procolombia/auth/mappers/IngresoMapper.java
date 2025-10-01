package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.IngresoResponseDTO;
import com.procol.procolombia.auth.entities.Ingreso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngresoMapper {

    @Mapping(source = "idUsuario.id", target = "idUsuario")
    IngresoResponseDTO toDto(Ingreso ingreso);
}
