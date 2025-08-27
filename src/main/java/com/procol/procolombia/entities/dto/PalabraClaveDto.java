package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.PalabraClave}
 */
public class PalabraClaveDto implements Serializable {
    private final Integer idPalabraClave;
    private final String textoPalabraClave;
    private final List<Integer> idUsuarios;
    private final List<Integer> idVacantes;

    public PalabraClaveDto(Integer idPalabraClave, String textoPalabraClave, List<Integer> usuarioIdUsuarios, List<Integer> vacanteIdVacantes) {
        this.idPalabraClave = idPalabraClave;
        this.textoPalabraClave = textoPalabraClave;
        this.idUsuarios = usuarioIdUsuarios;
        this.idVacantes = vacanteIdVacantes;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    public String getTextoPalabraClave() {
        return textoPalabraClave;
    }

    public List<Integer> getIdUsuarios() {
        return idUsuarios;
    }

    public List<Integer> getIdVacantes() {
        return idVacantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalabraClaveDto entity = (PalabraClaveDto) o;
        return Objects.equals(this.idPalabraClave, entity.idPalabraClave) &&
                Objects.equals(this.textoPalabraClave, entity.textoPalabraClave) &&
                Objects.equals(this.idUsuarios, entity.idUsuarios) &&
                Objects.equals(this.idVacantes, entity.idVacantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPalabraClave, textoPalabraClave, idUsuarios, idVacantes);
    }
}