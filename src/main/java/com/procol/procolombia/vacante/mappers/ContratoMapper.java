package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.ContratoDto;
import com.procol.procolombia.vacante.entities.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratoMapper {
    @Mapping(target = "vacantes", ignore = true)
    Contrato toContrato(ContratoDto contratoDto);
    ContratoDto toContratoDto(Contrato contrato);

    List<ContratoDto> toContratoDtos(List<Contrato> contratos);
}
