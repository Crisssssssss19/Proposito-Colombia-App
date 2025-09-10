package com.procol.procolombia.vacante.dto;

import com.procol.procolombia.vacante.entities.Requisito;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;


public record RequisitoDto(
    Integer id,
    Integer idVacante,
    String tituloRequisito,
    String detalleRequisito,
    Short ordenRequisito
) {}