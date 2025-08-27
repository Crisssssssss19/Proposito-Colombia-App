package com.procol.procolombia.entities.dto;

import com.procol.procolombia.entities.RelVacantePalabraclaveId;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.procol.procolombia.entities.RelVacantePalabraclave}
 */
public class RelVacantePalabraclaveDto implements Serializable {
    private final RelVacantePalabraclaveId id;
    private final Integer idVacante;
    private final Integer idPalabraClave;

    public RelVacantePalabraclaveDto(RelVacantePalabraclaveId id, Integer idVacanteId, Integer idPalabraClaveId) {
        this.id = id;
        this.idVacante = idVacanteId;
        this.idPalabraClave = idPalabraClaveId;
    }

    public RelVacantePalabraclaveId getId() {
        return id;
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
        RelVacantePalabraclaveDto entity = (RelVacantePalabraclaveDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idVacante, entity.idVacante) &&
                Objects.equals(this.idPalabraClave, entity.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idVacante, idPalabraClave);
    }
}