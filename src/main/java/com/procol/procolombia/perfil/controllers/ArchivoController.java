package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveArchivoFile;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import com.procol.procolombia.perfil.services.ArchivoService;
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
    public ResponseEntity<GetArchivo> subirArchivo(@PathVariable Integer idUsuario, @RequestParam("file") MultipartFile file) {
        SaveArchivoFile saveArchivoFile = new SaveArchivoFile(file);
        return ResponseEntity.ok(archivoService.SubirArchivo(idUsuario, saveArchivoFile));
    }

    @GetMapping("/verArchivos")
    public ResponseEntity<List<GetArchivo>> listarArchivos(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(archivoService.listarArchivosPorUsuario(idUsuario));
    }

    @DeleteMapping("/{idArchivo}")
    public ResponseEntity<Void> eliminarArchivo(@PathVariable Integer idArchivo) {
        archivoService.eliminarArchivo(idArchivo);
        return ResponseEntity.noContent().build();
    }

}
