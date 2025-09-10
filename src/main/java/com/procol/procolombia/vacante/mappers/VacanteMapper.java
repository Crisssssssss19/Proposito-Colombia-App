package com.procol.procolombia.vacante.mappers;


import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.vacante.dto.VacanteDto;
import com.procol.procolombia.vacante.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VacanteMapper {
    @Mapping(source = "idUbicacion.id", target = "idUbicacion")
    @Mapping(source = "anuncio.id", target = "anuncioId")
    @Mapping(source = "historialEstadoVacantes", target = "historialEstadosVacanteIds", qualifiedByName = "mapHistorialToIds")
    @Mapping(source = "postulaciones", target = "postulacionIds", qualifiedByName = "mapPostulacionesToIds")
    @Mapping(source = "palabraClaves", target = "palabrasClaveIds", qualifiedByName = "mapPalabrasToIds")
    @Mapping(source = "requisitos", target = "requisitoIds", qualifiedByName = "mapRequisitosToIds")
    VacanteDto toDto(Vacante entity);

    @Mapping(source = "idUbicacion", target = "idUbicacion", qualifiedByName = "mapUbicacion")
    @Mapping(source = "anuncioId", target = "anuncio", qualifiedByName = "mapAnuncio")
    @Mapping(source = "historialEstadosVacanteIds", target = "historialEstadoVacantes", qualifiedByName = "mapIdsToHistorial")
    @Mapping(source = "postulacionIds", target = "postulaciones", qualifiedByName = "mapIdsToPostulaciones")
    @Mapping(source = "palabrasClaveIds", target = "palabraClaves", qualifiedByName = "mapIdsToPalabras")
    @Mapping(source = "requisitoIds", target = "requisitos", qualifiedByName = "mapIdsToRequisitos")
    Vacante toEntity(VacanteDto dto);

    @Named("mapUbicacion")
    default Ubicacione mapUbicacion(Integer id) {
        if (id == null) return null;
        Ubicacione u = new Ubicacione();
        u.setId(id);
        return u;
    }

    @Named("mapAnuncio")
    default Anuncio mapAnuncio(Integer id) {
        if (id == null) return null;
        Anuncio a = new Anuncio();
        a.setId(id);
        return a;
    }

    @Named("mapHistorialToIds")
    default Set<Integer> mapHistorialToIds(Set<HistorialEstadoVacante> set) {
        if (set == null) return null;
        return set.stream().map(HistorialEstadoVacante::getId).collect(Collectors.toSet());
    }

    @Named("mapIdsToHistorial")
    default Set<HistorialEstadoVacante> mapIdsToHistorial(Set<Integer> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            HistorialEstadoVacante h = new HistorialEstadoVacante();
            h.setId(id);
            return h;
        }).collect(Collectors.toSet());
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
}
