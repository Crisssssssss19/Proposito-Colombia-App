package com.procol.procolombia.entities.dto;



import java.io.Serializable;

import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Interes}
 */
public class InteresDto implements Serializable {
    private final Integer idEmpresa;
    private final Integer idUsuario;
    private final Short tipoInteres;

    public InteresDto(Integer empresa, Integer usuario, Short tipoInteres) {
        this.idEmpresa = empresa;
        this.idUsuario = usuario;
        this.tipoInteres = tipoInteres;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Short getTipoInteres() {
        return tipoInteres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InteresDto entity = (InteresDto) o;
        return Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.tipoInteres, entity.tipoInteres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, idUsuario, tipoInteres);
    }
}