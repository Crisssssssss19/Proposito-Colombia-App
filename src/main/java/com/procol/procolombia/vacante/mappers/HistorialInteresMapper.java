package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.HistorialInteresDto;
import com.procol.procolombia.vacante.entities.HistorialInteres;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = InteresMapper.class)
public interface HistorialInteresMapper {
    @Mapping(source = "idInteres", target = "interes")
    HistorialInteres toHistorialInteres(HistorialInteresDto historialInteresDto);
    @Mapping(source = "interes", target = "idInteres")
    HistorialInteresDto toHistorialInteresDto(HistorialInteres historialInteres);

    List<HistorialInteresDto> toHistorialInteresDtos(List<HistorialInteres> historialInteres);
}
