package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveTalento;
import com.procol.procolombia.perfil.dtos.response.GetTalento;

import java.util.List;

public interface TalentoService {
    GetTalento crearTalento(SaveTalento saveTalento);

    GetTalento obtenerTalentoPorId(Integer id);

    GetTalento actualizarTalento(Integer id, SaveTalento saveTalento);

    void eliminarTalento(Integer id);

    List<GetTalento> ListarTalentos();

    List<GetTalento> ListarTalentosPorTipo(Short tipo);
}
