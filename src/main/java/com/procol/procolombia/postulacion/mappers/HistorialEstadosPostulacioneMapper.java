package com.procol.procolombia.postulacion.mappers;

import com.procol.procolombia.postulacion.entities.HistorialEstadosPostulacione;
import com.procol.procolombia.postulacion.dto.HistorialEstadosPostulacioneDto;

import java.util.List;
import java.util.stream.Collectors;

public class HistorialEstadosPostulacioneMapper {

    public static HistorialEstadosPostulacioneDto toDto(HistorialEstadosPostulacione entity) {
        if (entity == null) return null;

        return new HistorialEstadosPostulacioneDto(
                entity.getId(),
                entity.getIdPostulacion() != null ? entity.getIdPostulacion().getId() : null,
                entity.getIdEstadoPostulacion() != null ? entity.getIdEstadoPostulacion().getId() : null,
                entity.getFechaHistorialPostulacion(),
                entity.getDetalleHistorialPostulacion()
        );
    }

    public static HistorialEstadosPostulacione toEntity(HistorialEstadosPostulacioneDto dto) {
        if (dto == null) return null;

        HistorialEstadosPostulacione entity = new HistorialEstadosPostulacione();
        entity.setId(dto.getId());
        entity.setFechaHistorialPostulacion(dto.getFechaHistorialPostulacion());
        entity.setDetalleHistorialPostulacion(dto.getDetalleHistorialPostulacion());
        return entity;
    }

    public static List<HistorialEstadosPostulacioneDto> toDtoList(List<HistorialEstadosPostulacione> entities) {
        return entities == null ? null : entities.stream()
                .map(HistorialEstadosPostulacioneMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<HistorialEstadosPostulacione> toEntityList(List<HistorialEstadosPostulacioneDto> dtos) {
        return dtos == null ? null : dtos.stream()
                .map(HistorialEstadosPostulacioneMapper::toEntity)
                .collect(Collectors.toList());
    }
}