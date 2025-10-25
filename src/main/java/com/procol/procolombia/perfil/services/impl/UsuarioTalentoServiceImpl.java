package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveUsuarioTalento;
import com.procol.procolombia.perfil.dtos.response.GetUsuarioTalento;
import com.procol.procolombia.perfil.entities.Talento;
import com.procol.procolombia.perfil.entities.UsuarioTalento;
import com.procol.procolombia.perfil.repositories.TalentoRepository;
import com.procol.procolombia.perfil.repositories.UsuarioTalentoRepository;
import com.procol.procolombia.perfil.services.UsuarioTalentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioTalentoServiceImpl implements UsuarioTalentoService {
    private final UsuarioTalentoRepository usuarioTalentoRepository;
    private final TalentoRepository talentoRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioTalentoServiceImpl(UsuarioTalentoRepository usuarioTalentoRepository, TalentoRepository talentoRepository, UsuarioRepository usuarioRepository) {
        this.usuarioTalentoRepository = usuarioTalentoRepository;
        this.talentoRepository = talentoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    @Transactional(readOnly = false)
    public GetUsuarioTalento agregarTalento(Integer idUsuario, SaveUsuarioTalento saveUsuarioTalento) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Talento talento = talentoRepository.findById(saveUsuarioTalento.idTalento())
                .orElseThrow(() -> new RuntimeException("Talento no encontrado"));

        if (usuarioTalentoRepository.existsByUsuarioIdAndTalentoId(idUsuario, saveUsuarioTalento.idTalento())) {
            throw new RuntimeException("El talento ya está asignado al usuario");
        }

        UsuarioTalento usuarioTalento = new UsuarioTalento();
        usuarioTalento.setUsuario(usuario);
        usuarioTalento.setTalento(talento);
        usuarioTalento.setNivelDominio(saveUsuarioTalento.nivelDominio());

        usuarioTalentoRepository.save(usuarioTalento);

        return new GetUsuarioTalento(
                usuarioTalento.getId(),
                talento.getNombre(),
                talento.getTipo(),
                usuarioTalento.getNivelDominio()
        );
    }

    @Override
    @Transactional(readOnly = false)
    public List<GetUsuarioTalento> listarTalentosPorUsuario(Integer idUsuario) {
        return usuarioTalentoRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(ut -> new GetUsuarioTalento(
                        ut.getId(),
                        ut.getTalento().getNombre(),
                        ut.getTalento().getTipo(),
                        ut.getNivelDominio()
                ))
                .toList();
    }

    @Override
    @Transactional
    public GetUsuarioTalento actualizarNivelDominio(Integer idUsuario, Integer idUsuarioTalento, SaveUsuarioTalento saveUsuarioTalento) {
        UsuarioTalento usuarioTalento = usuarioTalentoRepository.findById(idUsuarioTalento)
                .orElseThrow(() -> new RuntimeException("UsuarioTalento no encontrado"));

        if (!usuarioTalento.getUsuario().getId().equals(idUsuario)) {
            throw new RuntimeException("El talento no pertenece al usuario especificado");
        }

        usuarioTalento.setNivelDominio(saveUsuarioTalento.nivelDominio());
        usuarioTalentoRepository.save(usuarioTalento);

        return new GetUsuarioTalento(
                usuarioTalento.getId(),
                usuarioTalento.getTalento().getNombre(),
                usuarioTalento.getTalento().getTipo(),
                usuarioTalento.getNivelDominio()
        );
    }

    @Override
    @Transactional
    public void eliminarUsuarioTalento(Integer idUsuarioTalento) {
        if (!usuarioTalentoRepository.existsById(idUsuarioTalento)) {
            throw new RuntimeException("UsuarioTalento no encontrado");
        }
        usuarioTalentoRepository.deleteById(idUsuarioTalento);
    }
}
