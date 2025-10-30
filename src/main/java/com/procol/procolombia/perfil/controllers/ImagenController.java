package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveImagenFile;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetImagenConUrl;
import com.procol.procolombia.perfil.services.ImagenService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/usuarios/{idUsuario}/imagenes")
public class ImagenController {
    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping("/subir")
    public ResponseEntity<ApiResponse<GetImagenConUrl>> subirImagen(
            @PathVariable Integer idUsuario,
            @RequestParam("File") MultipartFile file,
            @RequestParam(value = "favorita", defaultValue = "false") boolean favorita,
            @RequestParam(value = "categoria", defaultValue = "2") Short categoria
    ) {
        SaveImagenFile saveImagen = new SaveImagenFile(file, favorita,  categoria);
        return ResponseEntity.ok(ApiResponse.success("Imagene subida", imagenService.SubirImagen(idUsuario, saveImagen), HttpStatus.CREATED));
    }

    @DeleteMapping("/{idImagen}")
    public ResponseEntity<ApiResponse<Void>> eliminarImagen(@PathVariable Integer idImagen) {
        imagenService.eliminarImagen(idImagen);
        return ResponseEntity.ok(ApiResponse.success("Imagene eliminada correctamente", null, HttpStatus.OK));
    }

    @PutMapping("/{idImagen}/favorita")
    public ResponseEntity<ApiResponse<GetImagenConUrl>> marcarComoFavorita(@PathVariable Integer idImagen) {
        return ResponseEntity.ok(ApiResponse.success("Imagene de perfil cambiada", imagenService.marcarComoFavorita(idImagen), HttpStatus.OK));
        }

    @GetMapping("/ver/{filename}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename) {
        try {
            Path filePath = imagenService.obtenerRutaImagen(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/verPortafolio")
    public ResponseEntity<ApiResponse<List<GetImagenConUrl>>> obtenerImagenesPortafolio(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                "Lista de imágenes del portafolio",
                imagenService.listarImagenesPorCategoria(idUsuario, (short) 2), // Solo portafolio
                HttpStatus.OK
        ));
    }

    @GetMapping("/perfil")
    public ResponseEntity<ApiResponse<List<GetImagenConUrl>>> obtenerImagenesPerfil(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                "Imágenes de perfil",
                imagenService.listarImagenesPorCategoria(idUsuario, (short) 1),
                HttpStatus.OK
        ));
    }
}
