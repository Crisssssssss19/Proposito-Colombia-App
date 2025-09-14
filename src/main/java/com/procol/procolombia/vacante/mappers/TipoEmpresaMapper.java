package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.TipoEmpresaDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TipoEmpresaMapper {
    TipoEmpresaDto toDto(TipoEmpresa entity);

    @Mapping(target = "empresas", ignore = true)
    TipoEmpresa toEntity(TipoEmpresaDto dto);
}
