package com.procol.procolombia.auth.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pre_registro")
public class PreRegistro {
    @Id
    @Column(name = "id_pre_registro", nullable = false)
    private String idPreRegistro; // Número de teléfono con +57...

    @Column(name = "pin_pre_registro", nullable = false, length = 20)
    private String pinPreRegistro;

    @Column(name = "fecha_pre_registro", nullable = false)
    private LocalDateTime fechaPreRegistro;

    @Column(name = "estado_pre_registro", nullable = false)
    private Short estadoPreRegistro; // 1=normal, 2=reintento, 3=bloqueado

    @Column(name = "bloqueado_hasta", nullable = true)
    private LocalDateTime bloqueadoHasta;

    @Column(name = "intentos", nullable = false)
    private int intentos = 0;

    public Integer getIntentos() {
        return intentos;
    }
    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public String getIdPreRegistro() {
        return idPreRegistro;
    }
    public void setIdPreRegistro(String idPreRegistro) {
        this.idPreRegistro = idPreRegistro;
    }
    public String getPinPreRegistro() {
        return pinPreRegistro;
    }
    public void setPinPreRegistro(String pinPreRegistro) {
        this.pinPreRegistro = pinPreRegistro;
    }
    public LocalDateTime getFechaPreRegistro() {
        return fechaPreRegistro;
    }
    public void setFechaPreRegistro(LocalDateTime fechaPreRegistro) {
        this.fechaPreRegistro = fechaPreRegistro;
    }
    public Short getEstadoPreRegistro() {
        return estadoPreRegistro;
    }
    public void setEstadoPreRegistro(Short estadoPreRegistro) {
        this.estadoPreRegistro = estadoPreRegistro;
    }
    public LocalDateTime getBloqueadoHasta() {
        return bloqueadoHasta;
    }
    public void setBloqueadoHasta(LocalDateTime bloqueadoHasta) {
        this.bloqueadoHasta = bloqueadoHasta;
    }
}
