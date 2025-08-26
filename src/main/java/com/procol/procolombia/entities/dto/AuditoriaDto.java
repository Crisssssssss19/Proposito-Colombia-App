package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Auditoria}
 */
public class AuditoriaDto implements Serializable {
    private final Integer idAuditoria;
    private final String nombreEntidadAuditoria;
    private final Integer idReferenciaAuditoria;
    private final Date fechaAuditoria;
    private final String tipoCambioAuditoria;
    private final String comentarioAuditoria;
    private final Integer usuarioIdUsuario;

    public AuditoriaDto(Integer idAuditoria, String nombreEntidadAuditoria, Integer idReferenciaAuditoria, Date fechaAuditoria, String tipoCambioAuditoria, String comentarioAuditoria, Integer usuarioIdUsuario) {
        this.idAuditoria = idAuditoria;
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
        this.idReferenciaAuditoria = idReferenciaAuditoria;
        this.fechaAuditoria = fechaAuditoria;
        this.tipoCambioAuditoria = tipoCambioAuditoria;
        this.comentarioAuditoria = comentarioAuditoria;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public String getNombreEntidadAuditoria() {
        return nombreEntidadAuditoria;
    }

    public Integer getIdReferenciaAuditoria() {
        return idReferenciaAuditoria;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public String getTipoCambioAuditoria() {
        return tipoCambioAuditoria;
    }

    public String getComentarioAuditoria() {
        return comentarioAuditoria;
    }

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditoriaDto entity = (AuditoriaDto) o;
        return Objects.equals(this.idAuditoria, entity.idAuditoria) &&
                Objects.equals(this.nombreEntidadAuditoria, entity.nombreEntidadAuditoria) &&
                Objects.equals(this.idReferenciaAuditoria, entity.idReferenciaAuditoria) &&
                Objects.equals(this.fechaAuditoria, entity.fechaAuditoria) &&
                Objects.equals(this.tipoCambioAuditoria, entity.tipoCambioAuditoria) &&
                Objects.equals(this.comentarioAuditoria, entity.comentarioAuditoria) &&
                Objects.equals(this.usuarioIdUsuario, entity.usuarioIdUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuditoria, nombreEntidadAuditoria, idReferenciaAuditoria, fechaAuditoria, tipoCambioAuditoria, comentarioAuditoria, usuarioIdUsuario);
    }
}