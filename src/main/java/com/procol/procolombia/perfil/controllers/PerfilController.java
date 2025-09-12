package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.request.SaveArchivoFile;
import com.procol.procolombia.perfil.dtos.request.SaveImagenFile;
import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.*;
import com.procol.procolombia.perfil.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    private final UsuarioService usuarioService;
    private final AccesoService accesoService;
    private final ImagenService imagenService;
    private final ArchivoService archivoService;
    private final PerfilService perfilService;

    public PerfilController(UsuarioService usuarioService, AccesoService accesoService, ImagenService imagenService, ArchivoService archivoService, PerfilService perfilService) {
        this.usuarioService = usuarioService;
        this.accesoService = accesoService;
        this.imagenService = imagenService;
        this.archivoService = archivoService;
        this.perfilService = perfilService;
    }


    @GetMapping("/{id}/completo")
    public ResponseEntity<ApiResponse<GetPerfil>> obtnenerPerfilCompleto(@PathVariable Integer id) {
        GetPerfil perfil = perfilService.obtenerPerfilCompleto(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "perfil obtenido correctamente", perfil));
    }

    // USUARIO

    @PutMapping("/{id}")
    public ResponseEntity<GetUsuario> actualizarUsuario(@PathVariable Integer id, @RequestBody SaveUsuario saveUsuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, saveUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUsuario> getUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    // ACCESO

    @PutMapping("/{id}/acceso")
    public ResponseEntity<GetAcceso> actualizarAcceso(@PathVariable Integer id, @RequestBody SaveAcceso saveAcceso){
        return ResponseEntity.ok(accesoService.actualizarAcceso(id, saveAcceso));
    }

    @GetMapping("/{id}/acceso")
    public ResponseEntity<GetAcceso> obtenerAccesoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(accesoService.obtenerAccesoPorUsuarioId(id));
    }

    // IMAGEN
    @PostMapping("/{idUsuario}/imagenes")
    public ResponseEntity<GetImagen> subirImagen(@PathVariable Integer idUsuario, @RequestParam("file") MultipartFile file, @RequestParam(value = "favorita", defaultValue = "false") boolean favorita) {
        SaveImagenFile saveImagen = new SaveImagenFile(file, favorita);
        return ResponseEntity.ok(imagenService.SubirImagen(idUsuario, saveImagen));
    }

    @GetMapping("/{idUsuario}/imagenes")
    public ResponseEntity<List<GetImagen>> obtenerImagen(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(imagenService.listarImagenesPorUsuario(idUsuario));
    }

    @DeleteMapping("/{idImagen}/imagenes")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Integer idImagen) {
        imagenService.eliminarImagen(idImagen);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idImagen}/imagenes/favorita")
    public ResponseEntity<GetImagen> marcarComoFavorita(@PathVariable Integer idImagen) {
        return ResponseEntity.ok(imagenService.marcarComoFavorita(idImagen));
    }

    // ARCHIVO
    @PostMapping("/{idUsuario}/archivos")
    public ResponseEntity<GetArchivo> subirArchivo(@PathVariable Integer idUsuario, @RequestParam("file") MultipartFile file) {
        SaveArchivoFile saveArchivoFile = new SaveArchivoFile(file);
        return ResponseEntity.ok(archivoService.SubirArchivo(idUsuario, saveArchivoFile));
    }

    @DeleteMapping("/{idArchivo}/archivos")
    public ResponseEntity<Void> eliminarArchivo(@PathVariable Integer idArchivo) {
        archivoService.eliminarArchivo(idArchivo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idUsuario}/archivos")
    public ResponseEntity<List<GetArchivo>> listarArchivos(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(archivoService.listarArchivosPorUsuario(idUsuario));
    }
}
