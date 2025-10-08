package com.procol.procolombia.postulacion.mappers;

import com.procol.procolombia.postulacion.entities.Mensaje;
import com.procol.procolombia.postulacion.dto.MensajeDto;

import java.util.List;
import java.util.stream.Collectors;

public class MensajeMapper {

    public static MensajeDto toDto(Mensaje entity) {
        if (entity == null) return null;

        return new MensajeDto(
                entity.getId(),
                entity.getIdPostulacion() != null ? entity.getIdPostulacion().getId() : null,
                entity.getIdUsuarioResponde() != null ? entity.getIdUsuarioResponde().getId() : null,
                entity.getTextoMensaje(),
                entity.getFechaMensaje(),
                entity.getEstadoMensaje()
        );
    }

    public static Mensaje toEntity(MensajeDto dto) {
        if (dto == null) return null;

        Mensaje entity = new Mensaje();
        entity.setId(dto.getId());
        entity.setTextoMensaje(dto.getTextoMensaje());
        entity.setFechaMensaje(dto.getFechaMensaje());
        entity.setEstadoMensaje(dto.getEstadoMensaje());
        return entity;
    }

    public static List<MensajeDto> toDtoList(List<Mensaje> entities) {
        return entities == null ? null : entities.stream()
                .map(MensajeMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Mensaje> toEntityList(List<MensajeDto> dtos) {
        return dtos == null ? null : dtos.stream()
                .map(MensajeMapper::toEntity)
                .collect(Collectors.toList());
    }
}
