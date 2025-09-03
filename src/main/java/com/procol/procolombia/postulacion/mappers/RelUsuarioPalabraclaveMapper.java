package com.procol.procolombia.postulacion.mappers;

import com.procol.procolombia.postulacion.entities.RelUsuarioPalabraclave;
import com.procol.procolombia.postulacion.dto.RelUsuarioPalabraclaveDto;

import java.util.List;
import java.util.stream.Collectors;

public class RelUsuarioPalabraclaveMapper {

    public static RelUsuarioPalabraclaveDto toDto(RelUsuarioPalabraclave entity) {
        if (entity == null) return null;

        RelUsuarioPalabraclaveDto dto = new RelUsuarioPalabraclaveDto();
        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getIdUsuario() != null ? entity.getIdUsuario().getId() : null);
        dto.setIdPalabraClave(entity.getIdPalabraClave() != null ? entity.getIdPalabraClave().getId() : null);

        return dto;
    }

    public static RelUsuarioPalabraclave toEntity(RelUsuarioPalabraclaveDto dto) {
        if (dto == null) return null;

        RelUsuarioPalabraclave entity = new RelUsuarioPalabraclave();
        entity.setId(dto.getId());
        // Las relaciones (idUsuario, idPalabraClave) se setean a nivel de servicio
        return entity;
    }

    public static List<RelUsuarioPalabraclaveDto> toDtoList(List<RelUsuarioPalabraclave> entities) {
        return entities == null ? null : entities.stream()
                .map(RelUsuarioPalabraclaveMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<RelUsuarioPalabraclave> toEntityList(List<RelUsuarioPalabraclaveDto> dtos) {
        return dtos == null ? null : dtos.stream()
                .map(RelUsuarioPalabraclaveMapper::toEntity)
                .collect(Collectors.toList());
    }
}