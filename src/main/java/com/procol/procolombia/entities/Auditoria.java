package com.procol.procolombia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "auditorias")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditorias_id_gen")
    @SequenceGenerator(name = "auditorias_id_gen", sequenceName = "auditorias_id_auditoria_seq", allocationSize = 1)
    @Column(name = "id_auditoria", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_entidad_auditoria", nullable = false, length = 100)
    private String nombreEntidadAuditoria;

    @NotNull
    @Column(name = "id_referencia_auditoria", nullable = false)
    private Integer idReferenciaAuditoria;

    @NotNull
    @Column(name = "id_usuario_auditoria", nullable = false)
    private Integer idUsuarioAuditoria;

    @NotNull
    @Column(name = "fecha_auditoria", nullable = false)
    private Instant fechaAuditoria;

    @Size(max = 200)
    @NotNull
    @Column(name = "tipo_cambio_auditoria", nullable = false, length = 200)
    private String tipoCambioAuditoria;

    @NotNull
    @Column(name = "comentario_auditoria", nullable = false, length = Integer.MAX_VALUE)
    private String comentarioAuditoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEntidadAuditoria() {
        return nombreEntidadAuditoria;
    }

    public void setNombreEntidadAuditoria(String nombreEntidadAuditoria) {
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
    }

    public Integer getIdReferenciaAuditoria() {
        return idReferenciaAuditoria;
    }

    public void setIdReferenciaAuditoria(Integer idReferenciaAuditoria) {
        this.idReferenciaAuditoria = idReferenciaAuditoria;
    }

    public Integer getIdUsuarioAuditoria() {
        return idUsuarioAuditoria;
    }

    public void setIdUsuarioAuditoria(Integer idUsuarioAuditoria) {
        this.idUsuarioAuditoria = idUsuarioAuditoria;
    }

    public Instant getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Instant fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getTipoCambioAuditoria() {
        return tipoCambioAuditoria;
    }

    public void setTipoCambioAuditoria(String tipoCambioAuditoria) {
        this.tipoCambioAuditoria = tipoCambioAuditoria;
    }

    public String getComentarioAuditoria() {
        return comentarioAuditoria;
    }

    public void setComentarioAuditoria(String comentarioAuditoria) {
        this.comentarioAuditoria = comentarioAuditoria;
    }

}