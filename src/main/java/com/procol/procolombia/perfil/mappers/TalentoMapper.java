package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.perfil.dtos.request.SaveTalento;
import com.procol.procolombia.perfil.dtos.response.GetTalento;
import com.procol.procolombia.perfil.entities.Talento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TalentoMapper {

    Talento SaveTalentoToTalento(SaveTalento saveTalento);

    GetTalento TalentoToGetTalento(Talento talento);

    List<GetTalento> ListTalentoToListGetTalento(List<Talento> talentos);
}
