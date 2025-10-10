package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.RangoSalarialDto;
import com.procol.procolombia.vacante.entities.RangoSalarial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RangoSalarialMapper {
    @Mapping(target = "vacantes", ignore = true)
    RangoSalarial toRangoSalarial(RangoSalarialDto rangoSalarial);
    RangoSalarialDto toRangoSalarialDto(RangoSalarial rangoSalarial);

    List<RangoSalarialDto> toRangosSalarialDto(List<RangoSalarial> rangosSalariales);
}
