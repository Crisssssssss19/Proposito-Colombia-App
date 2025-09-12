package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveArchivoFile;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import com.procol.procolombia.perfil.services.ArchivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
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

}
