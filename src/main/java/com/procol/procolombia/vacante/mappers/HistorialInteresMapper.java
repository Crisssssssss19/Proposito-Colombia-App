package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.HistorialInteresDto;
import com.procol.procolombia.vacante.entities.HistorialInteres;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistorialInteresMapper {
    @Mapping(target = "interes.id", source = "idInteres")
    HistorialInteres toHistorialInteres(HistorialInteresDto historialInteresDto);
    @Mapping(target = "idInteres", source = "interes.id")
    HistorialInteresDto toHistorialInteresDto(HistorialInteres historialInteres);

    List<HistorialInteresDto> toHistorialInteresDtos(List<HistorialInteres> historialInteres);
}
