package com.procol.procolombia.postulacion.mappers;

import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.postulacion.entities.HistorialEstadosPostulacione;
import com.procol.procolombia.postulacion.entities.Mensaje;
import com.procol.procolombia.postulacion.dto.PostulacioneDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostulacioneMapper {

    public static PostulacioneDto toDto(Postulacione entity) {
        if (entity == null) return null;

        Set<Integer> historialIds = entity.getHistorialEstadosPostulaciones() != null ?
                entity.getHistorialEstadosPostulaciones().stream()
                        .map(HistorialEstadosPostulacione::getId)
                        .collect(Collectors.toSet()) : null;

        Set<Integer> mensajeIds = entity.getMensajes() != null ?
                entity.getMensajes().stream()
                        .map(Mensaje::getId)
                        .collect(Collectors.toSet()) : null;

        return new PostulacioneDto(
                entity.getId(),
                entity.getIdVacante() != null ? entity.getIdVacante().getId() : null,
                entity.getIdUsuario() != null ? entity.getIdUsuario().getId() : null,
                entity.getIdUsuario() != null ? entity.getIdUsuario().getEstadoUsuario() : null,
                entity.getFechaPostulacion(),
                entity.getCorrespondenciaPostulacion(),
                entity.getEstadoPostulacion(),
                historialIds,
                mensajeIds
        );
    }

    public static Postulacione toEntity(PostulacioneDto dto) {
        if (dto == null) return null;

        Postulacione entity = new Postulacione();
        entity.setId(dto.getId());
        entity.setFechaPostulacion(dto.getFechaPostulacion());
        entity.setCorrespondenciaPostulacion(dto.getCorrespondenciaPostulacion());
        entity.setEstadoPostulacion(dto.getEstadoPostulacion());
        // Las relaciones (idVacante, idUsuario) se setean a nivel de servicio
        return entity;
    }

    public static List<PostulacioneDto> toDtoList(List<Postulacione> entities) {
        return entities == null ? null : entities.stream()
                .map(PostulacioneMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Postulacione> toEntityList(List<PostulacioneDto> dtos) {
        return dtos == null ? null : dtos.stream()
                .map(PostulacioneMapper::toEntity)
                .collect(Collectors.toList());
    }
}