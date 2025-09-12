package com.procol.procolombia.perfil.services.impl;

import com.procol.procolombia.auth.entities.Ubicacione;
import com.procol.procolombia.auth.repositories.UbicacioneRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.perfil.dtos.response.*;
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

    public PerfilServiceImpl(UsuarioService usuarioService, ImagenService imagenService, ArchivoService archivoService, AccesoService accesoService, UsuarioRepository usuarioRepository, UbicacioneRepository ubicacioneRepository) {
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
        this.archivoService = archivoService;
        this.accesoService = accesoService;
        this.ubicacioneRepository = ubicacioneRepository;
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

        return new GetPerfil(
                usuario.id(),
                usuario.nombresUsuario(),
                usuario.apellidosUsuario(),
                acceso.email(),
                acceso.telefono(),
                usuario.idUbicacion().toString(),
                imagenes,
                archivos
        );
    }
}
