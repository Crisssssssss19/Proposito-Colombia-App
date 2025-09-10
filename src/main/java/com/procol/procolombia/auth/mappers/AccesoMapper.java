package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.entities.Acceso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccesoMapper {

    AccesoResponseDTO toDto(Acceso acceso);
    Acceso toEntity(AccesoResponseDTO accesoResponseDTO);
}
