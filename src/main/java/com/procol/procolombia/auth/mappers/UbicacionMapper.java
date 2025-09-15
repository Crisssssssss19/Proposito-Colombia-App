package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.Response.UbicacionResponseDTO;
import com.procol.procolombia.auth.entities.Ubicacione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {

    @Mapping(source = "id", target = "idUbicacion")
    @Mapping(source = "idPadreUbicacion.id", target = "idPadreUbicacion")
    UbicacionResponseDTO toDto(Ubicacione ubicacione);
}
