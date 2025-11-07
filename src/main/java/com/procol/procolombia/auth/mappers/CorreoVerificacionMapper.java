package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.CorreoVerificacionResponseDTO;
import com.procol.procolombia.auth.entities.CorreoVerificacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CorreoVerificacionMapper {
    CorreoVerificacionResponseDTO toDto(CorreoVerificacion correoVerificacion);
}
