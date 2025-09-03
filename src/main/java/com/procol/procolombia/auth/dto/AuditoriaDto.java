package com.procol.procolombia.auth.dto;

import com.procol.procolombia.auth.entities.Auditoria;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Auditoria}
 */
public class AuditoriaDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 100)
    private final String nombreEntidadAuditoria;
    @NotNull
    private final Integer idReferenciaAuditoria;
    @NotNull
    private final Integer idUsuarioAuditoria;
    @NotNull
    private final Instant fechaAuditoria;
    @NotNull
    @Size(max = 200)
    private final String tipoCambioAuditoria;
    @NotNull
    private final String comentarioAuditoria;

    public AuditoriaDto(Integer id, String nombreEntidadAuditoria, Integer idReferenciaAuditoria, Integer idUsuarioAuditoria, Instant fechaAuditoria, String tipoCambioAuditoria, String comentarioAuditoria) {
        this.id = id;
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
        this.idReferenciaAuditoria = idReferenciaAuditoria;
        this.idUsuarioAuditoria = idUsuarioAuditoria;
        this.fechaAuditoria = fechaAuditoria;
        this.tipoCambioAuditoria = tipoCambioAuditoria;
        this.comentarioAuditoria = comentarioAuditoria;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreEntidadAuditoria() {
        return nombreEntidadAuditoria;
    }

    public Integer getIdReferenciaAuditoria() {
        return idReferenciaAuditoria;
    }

    public Integer getIdUsuarioAuditoria() {
        return idUsuarioAuditoria;
    }

    public Instant getFechaAuditoria() {
        return fechaAuditoria;
    }

    public String getTipoCambioAuditoria() {
        return tipoCambioAuditoria;
    }

    public String getComentarioAuditoria() {
        return comentarioAuditoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditoriaDto entity = (AuditoriaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombreEntidadAuditoria, entity.nombreEntidadAuditoria) &&
                Objects.equals(this.idReferenciaAuditoria, entity.idReferenciaAuditoria) &&
                Objects.equals(this.idUsuarioAuditoria, entity.idUsuarioAuditoria) &&
                Objects.equals(this.fechaAuditoria, entity.fechaAuditoria) &&
                Objects.equals(this.tipoCambioAuditoria, entity.tipoCambioAuditoria) &&
                Objects.equals(this.comentarioAuditoria, entity.comentarioAuditoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreEntidadAuditoria, idReferenciaAuditoria, idUsuarioAuditoria, fechaAuditoria, tipoCambioAuditoria, comentarioAuditoria);
    }
}