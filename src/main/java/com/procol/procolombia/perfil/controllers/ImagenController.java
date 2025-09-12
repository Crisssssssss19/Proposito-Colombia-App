package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveImagenFile;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import com.procol.procolombia.perfil.services.ImagenService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<GetImagen>> subirImagen(@PathVariable Integer idUsuario, @RequestParam("File") MultipartFile file, @RequestParam(value = "favorita", defaultValue = "false") boolean favorita) {
        SaveImagenFile saveImagen = new SaveImagenFile(file, favorita);
        return ResponseEntity.ok(ApiResponse.success("Imagen subida", imagenService.SubirImagen(idUsuario, saveImagen), HttpStatus.CREATED));
    }

    @GetMapping("verImagenes")
    public ResponseEntity<ApiResponse<List<GetImagen>>> obtenerImagen(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(ApiResponse.success("Lista de imagenes obtenida correctamente", imagenService.listarImagenesPorUsuario(idUsuario), HttpStatus.OK));
    }

    @DeleteMapping("/{idImagen}")
    public ResponseEntity<ApiResponse<Void>> eliminarImagen(@PathVariable Integer idImagen) {
        imagenService.eliminarImagen(idImagen);
        return ResponseEntity.ok(ApiResponse.success("Imagen eliminada correctamente", null, HttpStatus.OK));
    }

    @PutMapping("/{idImagen}/favorita")
    public ResponseEntity<ApiResponse<GetImagen>> marcarComoFavorita(@PathVariable Integer idImagen) {
        return ResponseEntity.ok(ApiResponse.success("Imagen de perfil cambiada", imagenService.marcarComoFavorita(idImagen), HttpStatus.OK));
        }
}
