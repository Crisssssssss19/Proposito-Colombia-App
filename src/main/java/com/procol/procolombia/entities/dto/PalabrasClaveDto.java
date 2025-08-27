package com.procol.procolombia.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.procol.procolombia.entities.PalabrasClave}
 */
public class PalabrasClaveDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(max = 150)
    private final String textoPalabraClave;
    private final Set<Integer> usuarioIds;
    private final Set<Integer> vacanteIds;

    public PalabrasClaveDto(Integer id, String textoPalabraClave, Set<Integer> usuarioIds, Set<Integer> vacanteIds) {
        this.id = id;
        this.textoPalabraClave = textoPalabraClave;
        this.usuarioIds = usuarioIds;
        this.vacanteIds = vacanteIds;
    }

    public Integer getId() {
        return id;
    }

    public String getTextoPalabraClave() {
        return textoPalabraClave;
    }

    public Set<Integer> getUsuarioIds() {
        return usuarioIds;
    }

    public Set<Integer> getVacanteIds() {
        return vacanteIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalabrasClaveDto entity = (PalabrasClaveDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.textoPalabraClave, entity.textoPalabraClave) &&
                Objects.equals(this.usuarioIds, entity.usuarioIds) &&
                Objects.equals(this.vacanteIds, entity.vacanteIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textoPalabraClave, usuarioIds, vacanteIds);
    }
}