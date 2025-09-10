package com.procol.procolombia.auth.dto.Response;

public record AuditoriaResponseDTO(
        Integer idAuditoria,
        String nombreEntidadAuditoria,
        Integer idReferenciaAuditoria,
        Integer idUsuarioAuditoria,
        String tipoCambioAuditoria,
        String comentarioAuditoria,
        String fechaAuditoria
) {}
