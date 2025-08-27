package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.IntereseId;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.Interese}
 */
public class IntereseDto implements Serializable {
    private final IntereseId id;
    private final Integer idEmpresa;
    private final Integer idUsuario;
    @NotNull
    private final Short tipoInteres;

    public IntereseDto(IntereseId id, Integer idEmpresaId, Integer idUsuarioId, Short tipoInteres) {
        this.id = id;
        this.idEmpresa = idEmpresaId;
        this.idUsuario = idUsuarioId;
        this.tipoInteres = tipoInteres;
    }

    public IntereseId getId() {
        return id;
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
        IntereseDto entity = (IntereseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idEmpresa, entity.idEmpresa) &&
                Objects.equals(this.idUsuario, entity.idUsuario) &&
                Objects.equals(this.tipoInteres, entity.tipoInteres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idEmpresa, idUsuario, tipoInteres);
    }
}