package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.PeriodicidadPagoDto;
import com.procol.procolombia.vacante.entities.PeriodicidadPago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeriodicidadPagoMapper {
    @Mapping(target = "vacantes", ignore = true)
    PeriodicidadPago toPeriodicidadPago(PeriodicidadPagoDto periodicidadPago);
    PeriodicidadPagoDto toPeriodicidadPagoDto(PeriodicidadPago periodicidadPago);

    List<PeriodicidadPagoDto> toPeriodicidadPagoDtos(List<PeriodicidadPago> periodicidadPagos) ;
}
