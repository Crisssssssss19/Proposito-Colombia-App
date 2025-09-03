package com.procol.procolombia.postulacion.mappers;

import com.procol.procolombia.postulacion.entities.EstadosPostulacione;
import com.procol.procolombia.postulacion.entities.HistorialEstadosPostulacione;
import com.procol.procolombia.postulacion.dto.EstadosPostulacioneDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EstadosPostulacioneMapper {

    public static EstadosPostulacioneDto toDto(EstadosPostulacione estadosPostulacione) {
        if (estadosPostulacione == null) return null;

        Set<Integer> historialIds = estadosPostulacione.getHistorialEstadosPostulaciones() != null ?
                estadosPostulacione.getHistorialEstadosPostulaciones().stream()
                        .map(HistorialEstadosPostulacione::getId)
                        .collect(Collectors.toSet()) : null;

        EstadosPostulacioneDto dto = new EstadosPostulacioneDto();
        dto.setId(estadosPostulacione.getId());
        dto.setNombreEstadoPostulacion(estadosPostulacione.getNombreEstadoPostulacion());
        dto.setOrdenEstadoPostulacion(estadosPostulacione.getOrdenEstadoPostulacion());
        dto.setHistorialEstadosPostulacioneIds(historialIds);

        return dto;
    }

    public static EstadosPostulacione toEntity(EstadosPostulacioneDto dto) {
        if (dto == null) return null;

        EstadosPostulacione entity = new EstadosPostulacione();
        entity.setId(dto.getId());
        entity.setNombreEstadoPostulacion(dto.getNombreEstadoPostulacion());
        entity.setOrdenEstadoPostulacion(dto.getOrdenEstadoPostulacion());
        return entity;
    }

    public static List<EstadosPostulacioneDto> toDtoList(List<EstadosPostulacione> entities) {
        return entities == null ? null : entities.stream()
                .map(EstadosPostulacioneMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<EstadosPostulacione> toEntityList(List<EstadosPostulacioneDto> dtos) {
        return dtos == null ? null : dtos.stream()
                .map(EstadosPostulacioneMapper::toEntity)
                .collect(Collectors.toList());
    }
}
