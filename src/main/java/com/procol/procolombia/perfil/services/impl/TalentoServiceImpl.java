package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.perfil.dtos.request.SaveTalento;
import com.procol.procolombia.perfil.dtos.response.GetTalento;
import com.procol.procolombia.perfil.entities.Talento;
import com.procol.procolombia.perfil.mappers.TalentoMapper;
import com.procol.procolombia.perfil.repositories.TalentoRepository;
import com.procol.procolombia.perfil.services.TalentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TalentoServiceImpl implements TalentoService {

    private TalentoRepository talentoRepository;
    private TalentoMapper talentoMapper;

    public TalentoServiceImpl(TalentoRepository talentoRepository, TalentoMapper talentoMapper) {
        this.talentoRepository = talentoRepository;
        this.talentoMapper = talentoMapper;
    }

    @Override
    public GetTalento crearTalento(SaveTalento saveTalento) {
        talentoRepository.findByNombreAndTipo(saveTalento.nombre(),  saveTalento.tipo())
                .ifPresent(talento -> {
                    throw new IllegalArgumentException("El talento con nombre " + saveTalento.nombre() +
                            " con el tipo " + saveTalento.tipo() + " ya existe.");
                });

        Talento talento = talentoMapper.SaveTalentoToTalento(saveTalento);
        talento = talentoRepository.save(talento);
        return talentoMapper.TalentoToGetTalento(talento);
    }

    @Override
    public GetTalento obtenerTalentoPorId(Integer id) {
        Talento talento = talentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El talento con el id " + id + " no existe."));
        return talentoMapper.TalentoToGetTalento(talento);
    }

    @Override
    public GetTalento actualizarTalento(Integer id, SaveTalento saveTalento) {
        Talento talento = talentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El talento con el id " + id + " no existe."));

        talentoRepository.findByNombreAndTipo(talento.getNombre(), talento.getTipo())
                .ifPresent(existing -> {;
                    if (!existing.getId().equals(id)) {
                        throw new IllegalArgumentException("El talento con nombre " + saveTalento.nombre() +
                                " con el tipo " + saveTalento.tipo() + " ya existe.");
                    }
                });

        talento.setNombre(saveTalento.nombre());
        talento.setTipo(saveTalento.tipo());

        talento = talentoRepository.save(talento);
        return talentoMapper.TalentoToGetTalento(talento);
    }

    @Override
    public void eliminarTalento(Integer id) {
        if (!talentoRepository.existsById(id)) {
            throw new RuntimeException("El talento con el id " + id + " no existe.");
        }
        talentoRepository.deleteById(id);
    }

    @Override
    public List<GetTalento> ListarTalentos() {
        List<Talento> talentos = talentoRepository.findAll();
        if (talentos.isEmpty()) {
            throw new RuntimeException("No hay talentos registrados.");
        }
        return talentoMapper.ListTalentoToListGetTalento(talentos);
    }

    @Override
    public List<GetTalento> ListarTalentosPorTipo(Short tipo) {
        List<Talento> talentos = talentoRepository.findByTipo(tipo);
        if (talentos.isEmpty()) {
            throw new RuntimeException("No hay talentos registrados para el tipo " + tipo + ".");
        }
        return talentoMapper.ListTalentoToListGetTalento(talentos);
    }
}
