package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.UbicacionResponseDTO;
import com.procol.procolombia.auth.entities.Ubicacione;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {

    UbicacionResponseDTO toDto(Ubicacione ubicacione);
}
