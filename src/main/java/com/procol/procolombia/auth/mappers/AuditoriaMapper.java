package com.procol.procolombia.auth.mappers;

import com.procol.procolombia.auth.dto.AuditoriaDto;
import com.procol.procolombia.auth.entities.Auditoria;

import java.util.List;
import java.util.stream.Collectors;

public class AuditoriaMapper {

    public static AuditoriaDto toDto(Auditoria auditoria) {
        if (auditoria == null) return null;
        AuditoriaDto auditoriaDto = new AuditoriaDto();
        auditoriaDto.setId(auditoria.getId());
        auditoriaDto.setNombreEntidadAuditoria(auditoria.getNombreEntidadAuditoria());
        auditoriaDto.setIdReferenciaAuditoria(auditoria.getIdReferenciaAuditoria());
        auditoriaDto.setComentarioAuditoria(auditoria.getComentarioAuditoria());
        auditoriaDto.setFechaAuditoria(auditoria.getFechaAuditoria());
        auditoriaDto.setTipoCambioAuditoria(auditoria.getTipoCambioAuditoria());
        return auditoriaDto;
    }

    public static Auditoria toEntity(AuditoriaDto dto) {
        if (dto == null) return null;
        Auditoria auditoria = new Auditoria();
        auditoria.setId(dto.getId());
        auditoria.setNombreEntidadAuditoria(dto.getNombreEntidadAuditoria());
        auditoria.setIdReferenciaAuditoria(dto.getIdReferenciaAuditoria());
        auditoria.setComentarioAuditoria(dto.getComentarioAuditoria());
        auditoria.setFechaAuditoria(dto.getFechaAuditoria());
        auditoria.setTipoCambioAuditoria(dto.getTipoCambioAuditoria());
        return auditoria;
    }

    public static List<AuditoriaDto> toDto(List<Auditoria> auditorias) {
        return auditorias == null ? null : auditorias.stream().map(AuditoriaMapper::toDto).collect(Collectors.toList());
    }

    public static List<Auditoria> toEntity(List<AuditoriaDto> dtos) {
        return dtos == null ? null : dtos.stream().map(AuditoriaMapper::toEntity).collect(Collectors.toList());
    }
}
