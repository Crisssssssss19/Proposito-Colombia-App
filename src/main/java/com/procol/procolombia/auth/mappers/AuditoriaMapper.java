package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.AuditoriaResponseDTO;
import com.procol.procolombia.auth.entities.Auditoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditoriaMapper {

    AuditoriaResponseDTO toDto(Auditoria auditoria);
}
