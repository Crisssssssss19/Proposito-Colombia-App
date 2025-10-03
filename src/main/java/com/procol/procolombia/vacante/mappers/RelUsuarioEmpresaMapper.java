package com.procol.procolombia.vacante.mappers;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaIdDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;
import com.procol.procolombia.vacante.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RelUsuarioEmpresaMapper {
    RelUsuarioEmpresaIdDto toDto(RelUsuarioEmpresaId relUsuarioEmpresaId);
    RelUsuarioEmpresaId toEntity(RelUsuarioEmpresaIdDto relUsuarioEmpresaIdDto);

    @Mapping(source = "id", target = "id")
    RelUsuarioEmpresaDto toDto(RelUsuarioEmpresa entity);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "vacantes", ignore = true)
    RelUsuarioEmpresa toEntity(RelUsuarioEmpresaDto dto);

    Set<RelUsuarioEmpresa> toListEntity(Set<RelUsuarioEmpresaDto> dtos);
    Set<RelUsuarioEmpresaDto> toListDto(Set<RelUsuarioEmpresa> entities);


}
