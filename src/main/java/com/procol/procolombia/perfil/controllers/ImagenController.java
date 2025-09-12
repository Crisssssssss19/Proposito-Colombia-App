package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveImagenFile;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import com.procol.procolombia.perfil.services.ImagenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{idUsuario}/imagenes")
public class ImagenController {
    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping("/subir")
    public ResponseEntity<GetImagen> subirImagen(@PathVariable Integer idUsuario, @RequestParam("File") MultipartFile file, @RequestParam(value = "favorita", defaultValue = "false") boolean favorita) {
        SaveImagenFile saveImagen = new SaveImagenFile(file, favorita);
        return ResponseEntity.ok(imagenService.SubirImagen(idUsuario, saveImagen));
    }

    @GetMapping("verImagenes")
    public ResponseEntity<List<GetImagen>> obtenerImagen(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(imagenService.listarImagenesPorUsuario(idUsuario));
    }

    @DeleteMapping("/{idImagen}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Integer idImagen) {
        imagenService.eliminarImagen(idImagen);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idImagen}/favorita")
    public ResponseEntity<GetImagen> marcarComoFavorita(@PathVariable Integer idImagen) {
        return ResponseEntity.ok(imagenService.marcarComoFavorita(idImagen));
        }
}
