package com.procol.procolombia.vacante.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private int codigoEstado;
    private String mensaje;
    private T datos;
    private LocalDateTime fechaHora;

    public ApiResponse(int codigoEstado, String mensaje, T datos) {
        this.codigoEstado = codigoEstado;
        this.mensaje = mensaje;
        this.datos = datos;
        this.fechaHora = LocalDateTime.now();
    }

    // Getters y Setters
    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
