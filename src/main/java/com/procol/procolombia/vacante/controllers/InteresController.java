package com.procol.procolombia.vacante.controllers;


import com.procol.procolombia.vacante.dto.InteresIdDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.InteresService;
import com.procol.procolombia.vacante.dto.InteresDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intereses")
public class InteresController {
    private final InteresService interesService;

    public InteresController(InteresService interesService) {
        this.interesService = interesService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InteresDto>> createInteres(@RequestBody InteresDto interesDto) {
        InteresDto created = interesService.createInteres(interesDto);
        ApiResponse<InteresDto> response = new ApiResponse<>(
                200,
                "Interes creado exitosamente",
                created
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/obtener/{idUsuario}/{idEmpresa}")
    public ResponseEntity<ApiResponse<InteresDto>> getInteresById(@PathVariable int idUsuario, @PathVariable int idEmpresa) {
        Optional<InteresDto> interes = interesService.getInteresById(idUsuario,idEmpresa);
        if(interes.isPresent()) {
            ApiResponse<InteresDto> response = new ApiResponse<>(
                    200,
                    "interes obtenido por id",
                    interes.get()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<ApiResponse<InteresDto>> updateInteres(@RequestBody InteresDto interesDto) {
        InteresDto updated = interesService.updateInteres(interesDto);
        ApiResponse<InteresDto> response = new ApiResponse<>(
                200,
                "Interes creado exitosamente",
                updated
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idEmpresa}/{idUsuario}")
    public ResponseEntity<ApiResponse<Void>> deleteInteres(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idUsuario) {
        interesService.deleteInteresById(idUsuario, idEmpresa);
        ApiResponse<Void> response = new ApiResponse<>(
                200,
                "Interes eliminado exitosamente",
                null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idEmpresa}/{idUsuario}/tipo")
    public ResponseEntity<ApiResponse<Short>> obtenerTipoInteres(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idUsuario) {
        Short tipo = interesService.obtenerTipoInteres(idUsuario, idEmpresa);
        ApiResponse<Short> response = new ApiResponse<>(
                200,
                "Consulta Exitosa",
                tipo
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{idUsuario}/empresas")
    public ResponseEntity<ApiResponse<List<Integer>>> obtenerEmpresasSeguidas(
            @PathVariable Integer idUsuario,
            @RequestParam Short tipoInteres) {
        List<Integer> empresas = interesService.obtenerEmpresasSeguidasporUsuario(idUsuario, tipoInteres);
        ApiResponse<List<Integer>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                empresas
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/empresa/{idEmpresa}/usuarios")
    public ResponseEntity<ApiResponse<List<Integer>>> obtenerUsuariosSeguidos(
            @PathVariable Integer idEmpresa,
            @RequestParam Short tipoInteres) {
        List<Integer> usuarios = interesService.obtenerUsuariosSeguidasporEmpresa(idEmpresa, tipoInteres);
        ApiResponse<List<Integer>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                usuarios
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/match")
    public ResponseEntity<ApiResponse<List<InteresDto>>> obtenerRelacionesMutuas() {
        List<InteresDto> relaciones = interesService.obtenerRelacionesMutuas();
        if (relaciones.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 si no hay nada
        }
        ApiResponse<List<InteresDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa, Se hizo match",
                relaciones
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{idEmpresa}/{idUsuario}/usuarioSigueEmpresa")
    public ResponseEntity<Void> usuarioSigueEmpresa(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idUsuario) {
        interesService.usuarioSigueEmpresa(idUsuario, idEmpresa);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idEmpresa}/{idUsuario}/empresaSigueUsuario")
    public ResponseEntity<Void> empresaSigueUsuario(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idUsuario) {
        interesService.empresaSigueUsuario(idEmpresa, idUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idEmpresa}/{idUsuario}/mutuo")
    public ResponseEntity<Boolean> esSeguimientoMutuo(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idUsuario) {
        boolean result = interesService.esSeguimientoMutuo(idEmpresa, idUsuario);
        return ResponseEntity.ok(result);
    }
}
