package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.response.*;
import com.procol.procolombia.perfil.mappers.PerfilPalabraClaveMapper;
import com.procol.procolombia.perfil.mappers.TalentoMapper;
import com.procol.procolombia.perfil.services.*;
import com.procol.procolombia.vacante.entities.PalabraClave;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {
    private final UsuarioService usuarioService;
    private final ImagenService imagenService;
    private final ArchivoService archivoService;
    private final AccesoService accesoService;
    private final UbicacioneRepository ubicacioneRepository;
    private final UsuarioRepository usuarioRepository;
    private final TalentoMapper talentoMapper;
    private final PerfilPalabraClaveMapper palabraClaveMapper;
    private final PalabraClaveService palabraClaveService;

    public PerfilServiceImpl(UsuarioService usuarioService, ImagenService imagenService, ArchivoService archivoService, AccesoService accesoService, UbicacioneRepository ubicacioneRepository, UsuarioRepository usuarioRepository, TalentoMapper talentoMapper, PerfilPalabraClaveMapper palabraClaveMapper, PalabraClaveService palabraClaveService) {
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
        this.archivoService = archivoService;
        this.accesoService = accesoService;
        this.ubicacioneRepository = ubicacioneRepository;
        this.usuarioRepository = usuarioRepository;
        this.talentoMapper = talentoMapper;
        this.palabraClaveMapper = palabraClaveMapper;
        this.palabraClaveService = palabraClaveService;
    }

    @Override
    @Transactional(readOnly = true)
    public GetPerfil obtenerPerfilCompleto(Integer idUsuario) {
        GetUsuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        GetAcceso acceso = accesoService.obtenerAccesoPorUsuarioId(idUsuario);

        List<GetImagenConUrl> imagenes = imagenService.listarImagenesPorUsuario(idUsuario)
                .stream().toList();

        String urlFotoPerfil = imagenes.stream()
                .filter(GetImagenConUrl::favorita)
                .findFirst()
                .map(img -> img.url())
                .orElse(null);

        List<GetArchivoConUrl> archivos = archivoService.listarArchivosPorUsuario(idUsuario)
                .stream().map(arch -> new GetArchivoConUrl(
                        arch.id(),
                        arch.nombrePublico(),
                        "http://localhost:3210/uploads/archivos/" + arch.nombrePrivado(),
                        arch.tipo(),
                        arch.tamanio(),
                        arch.fechaSubida()
                )).toList();

        String ubicacionNombre = null;
        String ubicacionPadreNombre = null;

        if (usuario.idUbicacion() != null) {
            Ubicacione ubicacion = ubicacioneRepository.findById(usuario.idUbicacion())
                    .orElse(null);
            if (ubicacion != null) {
                ubicacionNombre = ubicacion.getNombreUbicacion();
                if (ubicacion.getIdPadreUbicacion() != null) {
                    ubicacionPadreNombre = ubicacion.getIdPadreUbicacion().getNombreUbicacion();
                    ubicacionNombre = ubicacionNombre + ", " + ubicacionPadreNombre;
                }
            }
        }

        Usuario usuarioEntity = usuarioRepository.findByIdWithDetalles(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<GetTalento> habilidades = usuarioEntity.getUsuarioTalentos().stream()
                .filter(ut -> ut.getTalento().getTipo() == 1)
                .map(ut -> {
                    GetTalento talentoDto = talentoMapper.TalentoToGetTalento(ut.getTalento());
                    return new GetTalento(
                            talentoDto.id(),
                            talentoDto.nombre(),
                            talentoDto.tipo()
                    );
                })
                .toList();

        List<GetTalento> competencias = usuarioEntity.getUsuarioTalentos().stream()
                .filter(ut -> ut.getTalento().getTipo() == 2)
                .map(ut -> {
                    GetTalento talentoDto = talentoMapper.TalentoToGetTalento(ut.getTalento());
                    return new GetTalento(
                            talentoDto.id(),
                            talentoDto.nombre(),
                            talentoDto.tipo()
                    );
                })
                .toList();

        List<GetPalabraClave> palabrasClave = usuarioEntity.getPalabrasClaves().stream()
                .map(palabraClaveMapper::PalabraClaveToGetPalabraClave)
                .toList();

        return new GetPerfil(
                usuario.id(),
                usuario.nombresUsuario(),
                usuario.apellidosUsuario(),
                acceso.email(),
                acceso.telefono(),
                ubicacionNombre,
                imagenes,
                archivos,
                habilidades,
                competencias,
                palabrasClave,
                obtenerHabilidadPrincipal(usuarioEntity),
                usuario.documentoUsuario(),
                urlFotoPerfil,
                usuario.profesion()
        );
    }

    @Override
    @Transactional
    public void actualizarPalabrasClave(Integer idUsuario, List<String> palabrasClave) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<PalabraClave> nuevas = palabraClaveService.asignarPalabras(palabrasClave);

        // Crear el set si está nulo
        if (usuario.getPalabrasClaves() == null) {
            usuario.setPalabrasClaves(new HashSet<>());
        } else {
            usuario.getPalabrasClaves().forEach(pc -> pc.getUsuarios().remove(usuario));
            usuario.getPalabrasClaves().clear();
        }

        if (palabrasClave != null && !palabrasClave.isEmpty()) {
            List<PalabraClave> nuevasPalabras = palabraClaveService.asignarPalabras(palabrasClave);

            nuevasPalabras.forEach(pc -> {
                usuario.getPalabrasClaves().add(pc);
                if (pc.getUsuarios() == null) {
                    pc.setUsuarios(new HashSet<>());
                }
                pc.getUsuarios().add(usuario);
            });
        }

        usuarioRepository.save(usuario);
    }


    private String obtenerHabilidadPrincipal(Usuario usuario) {
        if (usuario == null || usuario.getUsuarioTalentos() == null) {
            return null;
        }

        return usuario.getUsuarioTalentos().stream()
                .filter(ut -> ut != null && ut.getTalento() != null && ut.getTalento().getTipo() == 1)
                .filter(ut -> ut.getNivelDominio() != null)
                .max((ut1, ut2) -> ut1.getNivelDominio().compareTo(ut2.getNivelDominio()))
                .map(ut -> ut.getTalento().getNombre())
                .orElse(null);
    }
}
