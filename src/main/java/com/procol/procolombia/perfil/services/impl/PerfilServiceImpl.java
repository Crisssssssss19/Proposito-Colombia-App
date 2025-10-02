package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.response.*;
import com.procol.procolombia.perfil.mappers.PalabrasClaveMapper;
import com.procol.procolombia.perfil.mappers.TalentoMapper;
import com.procol.procolombia.perfil.services.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

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
    private final PalabrasClaveMapper palabraClaveMapper;

    public PerfilServiceImpl(UsuarioService usuarioService, ImagenService imagenService, ArchivoService archivoService, AccesoService accesoService, UbicacioneRepository ubicacioneRepository, UsuarioRepository usuarioRepository, TalentoMapper talentoMapper, PalabrasClaveMapper palabraClaveMapper) {
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
        this.archivoService = archivoService;
        this.accesoService = accesoService;
        this.ubicacioneRepository = ubicacioneRepository;
        this.usuarioRepository = usuarioRepository;
        this.talentoMapper = talentoMapper;
        this.palabraClaveMapper = palabraClaveMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public GetPerfil obtenerPerfilCompleto(Integer idUsuario) {
        GetUsuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        GetAcceso acceso = accesoService.obtenerAccesoPorUsuarioId(idUsuario);

        List<GetImagenConUrl> imagenes = imagenService.listarImagenesPorUsuario(idUsuario)
                .stream().map(img -> new GetImagenConUrl(
                        img.id(),
                        img.nombrePublico(),
                        "http://localhost:3210/uploads/imagenes/" + img.nombrePrivado(),
                        img.favorita() == 1,
                        img.fechaSubida()
                )).toList();

        List<GetArchivoConUrl> archivos = archivoService.listarArchivosPorUsuario(idUsuario)
                .stream().map(arch -> new GetArchivoConUrl(
                        arch.id(),
                        arch.nombrePublico(),
                        "http://localhost:3210/uploads/archivos/" + arch.nombrePrivado(),
                        arch.tipo(),
                        arch.tamanio(),
                        arch.fechaSubida()
                )).toList();

        String ubicacionNombre = usuario.idUbicacion() != null
                ? ubicacioneRepository.findById(usuario.idUbicacion())
                .map(Ubicacione::getNombreUbicacion)
                .orElse("Ubicación no disponible")
                : null;

        Usuario usuarioEntity = usuarioRepository.findByIdWithDetalles(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<GetTalento> habilidades = usuarioEntity.getTalentos().stream()
                .filter(t -> t.getTipo() == 1)
                .map(talentoMapper::TalentoToGetTalento)
                .toList();

        List<GetTalento> competencias = usuarioEntity.getTalentos().stream()
                .filter(t -> t.getTipo() == 2)
                .map(talentoMapper::TalentoToGetTalento)
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
                usuario.idUbicacion().toString(),
                imagenes,
                archivos,
                habilidades,
                competencias,
                palabrasClave
        );
    }
}
