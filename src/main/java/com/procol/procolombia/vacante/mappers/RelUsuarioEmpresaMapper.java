package com.procol.procolombia.vacante.mappers;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RelUsuarioEmpresaMapper {
    RelUsuarioEmpresa toEntity(RelUsuarioEmpresaDto relUsuarioEmpresaDto );
    RelUsuarioEmpresaDto toDto(RelUsuarioEmpresa relUsuarioEmpresa );
    Set<RelUsuarioEmpresa> toListEntity(Set<RelUsuarioEmpresaDto> relUsuarioEmpresaDtos);
    Set<RelUsuarioEmpresaDto> toListDto(Set<RelUsuarioEmpresa> relUsuarioEmpresas);
}
