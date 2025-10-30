package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveArchivoFile;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import com.procol.procolombia.perfil.services.ArchivoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("perfilArchivoController")
@RequestMapping("/usuarios/{idUsuario}/archivos")
public class ArchivoController {

    private final ArchivoService archivoService;

    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    @PostMapping("/subir")
    public ResponseEntity<ApiResponse<GetArchivo>> subirArchivo(@PathVariable Integer idUsuario, @RequestParam("file") MultipartFile file) {
        SaveArchivoFile saveArchivoFile = new SaveArchivoFile(file);
        return ResponseEntity.ok(ApiResponse.success("Archivo subido correctamente", archivoService.SubirArchivo(idUsuario, saveArchivoFile), HttpStatus.CREATED));
    }

    @GetMapping("/verArchivos")
    public ResponseEntity<ApiResponse<List<GetArchivo>>> listarArchivos(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(ApiResponse.success("Lista de archivos obtenida correctamente", archivoService.listarArchivosPorUsuario(idUsuario), HttpStatus.OK));
    }

    @DeleteMapping("/{idArchivo}")
    public ResponseEntity<ApiResponse<Void>> eliminarArchivo(@PathVariable Integer idArchivo) {
        archivoService.eliminarArchivo(idArchivo);
        return ResponseEntity.ok(ApiResponse.success("Archivo eliminado correctamente", null, HttpStatus.OK));
    }

    @GetMapping("/{idArchivo}")
    public ResponseEntity<ApiResponse<GetArchivo>> obtenerArchivo(@PathVariable Integer idUsuario, @PathVariable Integer idArchivo) {
        GetArchivo archivo = archivoService.obtenerArchivoPorId(idArchivo);
        return ResponseEntity.ok(ApiResponse.success("Archivo obtenido correctamente", archivo, HttpStatus.OK));
    }

    @GetMapping("/{idArchivo}/descargar")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable Integer idUsuario, @PathVariable Integer idArchivo) {
        GetArchivo archivo = archivoService.obtenerArchivoPorId(idArchivo);
        Resource recurso = archivoService.descargarArchivo(idArchivo, false);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(archivo.tipo()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + archivo.nombrePublico() + "\"")
                .body(recurso);
    }

    @GetMapping("/{idArchivo}/ver")
    public ResponseEntity<Resource> verArchivo(@PathVariable Integer idUsuario, @PathVariable Integer idArchivo) {
        GetArchivo archivo = archivoService.obtenerArchivoPorId(idArchivo);
        Resource recurso = archivoService.descargarArchivo(idArchivo, false);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(archivo.tipo()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + archivo.nombrePublico() + "\"")
                .body(recurso);
    }

}
