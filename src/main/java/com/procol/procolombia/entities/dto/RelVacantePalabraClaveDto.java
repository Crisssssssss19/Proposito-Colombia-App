package com.procol.procolombia.entities.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.RelVacantePalabraClave}
 */
public class RelVacantePalabraClaveDto implements Serializable {
    private final Integer idVacante;
    private final Integer idPalabraClave;

    public RelVacantePalabraClaveDto(Integer vacanteIdVacante, Integer palabraClaveIdPalabraClave) {
        this.idVacante = vacanteIdVacante;
        this.idPalabraClave = palabraClaveIdPalabraClave;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelVacantePalabraClaveDto entity = (RelVacantePalabraClaveDto) o;
        return Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.idPalabraClave, entity.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVacante, idPalabraClave);
    }
}