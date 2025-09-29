package com.procol.procolombia.vacante.mappers;


import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.postulacion.entities.Postulacione;
import com.procol.procolombia.vacante.dto.VacanteDto;
import com.procol.procolombia.vacante.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VacanteMapper {
    @Mapping(source = "idUbicacion.id", target = "idUbicacion")
    @Mapping(source = "palabrasClaves", target = "palabrasClaveIds", qualifiedByName = "mapPalabrasToIds")
    @Mapping(source = "requisitos", target = "requisitoIds", qualifiedByName = "mapRequisitosToIds")
    @Mapping(source = "rangoSalarial.id", target = "idRangoSalarial")
    @Mapping(source = "jornada.id", target = "idJornada")
    @Mapping(source = "modalidad.id", target = "idModalidad")
    @Mapping(source = "contrato.id", target = "idContrato")
    @Mapping(source = "relUsuarioEmpresas.id", target = "relUsuarioEmpresaId")
    @Mapping(source = "periodicidadPago.id", target = "idPeriodicidad")
    VacanteDto toDto(Vacante entity);

    @Mapping(source = "idUbicacion", target = "idUbicacion", qualifiedByName = "mapUbicacion")
    @Mapping(target = "historialEstadoVacantes", ignore=true)
    @Mapping(target = "postulaciones", ignore = true)
    @Mapping(source = "palabrasClaveIds", target = "palabrasClaves", qualifiedByName = "mapIdsToPalabras")
    @Mapping(source = "requisitoIds", target = "requisitos", qualifiedByName = "mapIdsToRequisitos")
    @Mapping(source = "idRangoSalarial", target = "rangoSalarial", qualifiedByName = "mapRango")
    @Mapping(source = "idJornada", target = "jornada", qualifiedByName = "mapJornada")
    @Mapping(source = "idModalidad", target = "modalidad", qualifiedByName = "mapModalidad")
    @Mapping(source = "idContrato", target = "contrato", qualifiedByName = "mapContrato")
    @Mapping(source = "relUsuarioEmpresaId", target = "relUsuarioEmpresas", qualifiedByName = "mapRelUsuarioEmpresa")
    @Mapping(source = "idPeriodicidad", target = "periodicidadPago", qualifiedByName = "mapPeriodicidad")
    Vacante toEntity(VacanteDto dto);

    @Named("mapUbicacion")
    default Ubicacione mapUbicacion(Integer id) {
        if (id == null) return null;
        Ubicacione u = new Ubicacione();
        u.setId(id);
        return u;
    }

    @Named("mapPalabrasToIds")
    default Set<Integer> mapPalabrasToIds(Set<PalabraClave> set) {
        if (set == null) return null;
        return set.stream().map(PalabraClave::getId).collect(Collectors.toSet());
    }

    @Named("mapIdsToPalabras")
    default Set<PalabraClave> mapIdsToPalabras(Set<Integer> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            PalabraClave p = new PalabraClave();
            p.setId(id);
            return p;
        }).collect(Collectors.toSet());
    }
    @Named("mapRequisitosToIds")
    default Set<Integer> mapRequisitosToIds(Set<Requisito> set) {
        if (set == null) return null;
        return set.stream().map(Requisito::getId).collect(Collectors.toSet());
    }

    @Named("mapIdsToRequisitos")
    default Set<Requisito> mapIdsToRequisitos(Set<Integer> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Requisito r = new Requisito();
            r.setId(id);
            return r;
        }).collect(Collectors.toSet());
    }

    @Named("mapRango")
    default RangoSalarial mapRango(Integer id) {
        if (id == null) return null;
        RangoSalarial r = new RangoSalarial();
        r.setId(id);
        return r;
    }

    @Named("mapJornada")
    default Jornada mapJornada(Integer id) {
        if (id == null) return null;
        Jornada j = new Jornada();
        j.setId(id);
        return j;
    }

    @Named("mapModalidad")
    default Modalidad mapModalidad(Integer id) {
        if (id == null) return null;
        Modalidad m = new Modalidad();
        m.setId(id);
        return m;
    }

    @Named("mapContrato")
    default Contrato mapContrato(Integer id) {
        if (id == null) return null;
        Contrato c = new Contrato();
        c.setId(id);
        return c;
    }
    @Named("mapPeriodicidad")
    default PeriodicidadPago mapPeriodicidad(Integer id) {
        if (id == null) return null;
        PeriodicidadPago p = new PeriodicidadPago();
        p.setId(id);
        return p;
    }

    @Named("mapRelUsuarioEmpresa")
    default RelUsuarioEmpresa mapRelUsuarioEmpresa(RelUsuarioEmpresaId id) {
        if (id == null) return null;
        RelUsuarioEmpresa rel = new RelUsuarioEmpresa();
        rel.setId(id);
        return rel;
    }

}
