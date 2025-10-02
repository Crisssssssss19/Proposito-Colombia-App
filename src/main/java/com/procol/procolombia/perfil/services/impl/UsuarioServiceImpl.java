package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.GetUsuario;
import com.procol.procolombia.perfil.entities.Talento;
import com.procol.procolombia.perfil.mappers.UsuarioMapper;
import com.procol.procolombia.perfil.repositories.TalentoRepository;
import com.procol.procolombia.perfil.services.UsuarioService;
import com.procol.procolombia.vacante.entities.PalabrasClave;
import com.procol.procolombia.vacante.repositories.PalabrasClaveRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    private UbicacioneRepository ubicacioneRepository;
    private TalentoRepository talentoRepository;
    private PalabrasClaveRepository palabrasClaveRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UbicacioneRepository ubicacioneRepository, TalentoRepository talentoRepository, PalabrasClaveRepository palabrasClaveRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.ubicacioneRepository = ubicacioneRepository;
        this.talentoRepository = talentoRepository;
        this.palabrasClaveRepository = palabrasClaveRepository;
    }

    @Override
    public GetUsuario crearUsuario(SaveUsuario saveUsuario) {
        Usuario usuario = usuarioMapper.saveUsuarioToUsuario(saveUsuario);
        if (saveUsuario.idUbicacion() != null) {
            usuario.setIdUbicacion(obtenerUbicacionPorId(saveUsuario.idUbicacion()));
        }
        Usuario usarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToGetUsuario(usarioGuardado);
    }

    @Override
    public GetUsuario actualizarUsuario(Integer id, SaveUsuario saveUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (saveUsuario.idUbicacion() != null) {
            usuario.setIdUbicacion(obtenerUbicacionPorId(saveUsuario.idUbicacion()));
        }

        usuario.getTalentos().clear();

        if(saveUsuario.habilidades() != null && !saveUsuario.habilidades().isBlank()) {
            List<Talento> habilidades = Arrays.stream(saveUsuario.habilidades().split(","))
                    .map(String::trim)
                    .map(nombre -> obtenerOrCrearTalento(nombre, (short) 1))
                    .toList();
            usuario.getTalentos().addAll(habilidades);
        }

        if(saveUsuario.competencias() != null && !saveUsuario.competencias().isBlank()) {
            List<Talento> competencias = Arrays.stream(saveUsuario.competencias().split(","))
                    .map(String::trim)
                    .map(nombre -> obtenerOrCrearTalento(nombre, (short) 2))
                    .toList();
            usuario.getTalentos().addAll(competencias);
        }

        usuario.getPalabrasClaves().clear();

        if(saveUsuario.palabrasClave() != null && !saveUsuario.palabrasClave().isEmpty()) {
            List<PalabrasClave> palabras = saveUsuario.palabrasClave().stream()
                    .map(String::trim)
                    .map(this::obtenerOcrearPalabraClave)
                    .toList();
            usuario.getPalabrasClaves().addAll(palabras);
        }

        usuario.setNombresUsuario(saveUsuario.nombres());
        usuario.setApellidosUsuario(saveUsuario.apellidos());
        usuario.setTipoDocumentoUsuario(saveUsuario.tipoDocumento());
        usuario.setEstadoUsuario(saveUsuario.estado());
        usuario.setDocumentoUsuario(saveUsuario.documento());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToGetUsuario(usuarioActualizado);
    }

    @Override
    public GetUsuario obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return usuarioMapper.usuarioToGetUsuario(usuario);
    }

    @Override
    public List<GetUsuario> listarUsuarios() {
        return usuarioMapper.usuarioListToGetUsuarioList(usuarioRepository.findAll());
    }

    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con id " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private Ubicacione obtenerUbicacionPorId(Integer id) {
        return ubicacioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ubicación no encontrada"));
    }

    private Talento obtenerOrCrearTalento(String nombre, Short tipo) {
        return talentoRepository.findByNombreAndTipo(nombre, tipo)
                .orElseGet(() -> {
                    Talento nuevoTalento = new Talento();
                    nuevoTalento.setNombre(nombre);
                    nuevoTalento.setTipo(tipo);
                    return talentoRepository.save(nuevoTalento);
                });
    }

    private PalabrasClave obtenerOcrearPalabraClave(String texto) {
        String textoNormalizado = texto.trim().toLowerCase();
        return palabrasClaveRepository.findByTextoPalabraClave(texto)
                .orElseGet(() -> {
                    PalabrasClave nuevaPalabraClave = new PalabrasClave();
                    nuevaPalabraClave.setTextoPalabraClave(texto);
                    return palabrasClaveRepository.save(nuevaPalabraClave);
                });
    }
}
