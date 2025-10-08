package com.procol.procolombia.vacante.controllers;


import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.VacanteService;
import com.procol.procolombia.vacante.dto.VacanteDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/vacantes")
public class VacanteController {
    private final VacanteService vacanteService;

    public VacanteController(VacanteService vacanteService) {
        this.vacanteService = vacanteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VacanteDto>>> getAllVacantes() {
        List<VacanteDto> vacantes = vacanteService.findAllVacantes();
        ApiResponse<List<VacanteDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                vacantes
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VacanteDto>> getVacanteById(@PathVariable Integer id) {
        VacanteDto vacante = vacanteService.getVacanteById(id);
        ApiResponse<VacanteDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                vacante
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VacanteDto>> createVacante(@RequestBody VacanteDto vacanteDto) {
        VacanteDto created = vacanteService.createVacante(vacanteDto);
        ApiResponse<VacanteDto> response = new ApiResponse<>(
                200,
                "Vacante creada exitosamente",
                created
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VacanteDto>> updateVacante(@PathVariable Integer id,
            @Valid @RequestBody VacanteDto vacanteDto) {
        VacanteDto updated = vacanteService.updateVacante(id, vacanteDto);
        ApiResponse<VacanteDto> response = new ApiResponse<>(
            201,
                "Vacante Actualizada Exitosamente",
                updated
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> deleteVacante(@PathVariable Integer id) {
        vacanteService.deleteVacante(id);
        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Vacante eliminada exitosamente",
                id
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<ApiResponse<List<VacanteDto>>> getVacantesByEmpresa(@PathVariable Integer empresaId) {
        List<VacanteDto> vacantes = vacanteService.getVacantesByEmpresa(empresaId);
        ApiResponse<List<VacanteDto>> response = new ApiResponse<>(
                200,
                "Consulta Exitosa",
                vacantes
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{idUsuario}/empresa/{idEmpresa}")
    public ResponseEntity<ApiResponse<List<VacanteDto>>> getVacantesByUsuarioAndEmpresa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idEmpresa) {
        List<VacanteDto> vacantes = vacanteService.getVacantesByUsuarioAndEmpresa(idUsuario, idEmpresa);
        ApiResponse<List<VacanteDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                vacantes
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/dias-publicados")
    public ResponseEntity<ApiResponse<Integer>> obtenerDiasPublicados (
            @PathVariable Integer id
    ){
        Integer diasPublicados = vacanteService.obtenerDiasPublicados(id);
        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                diasPublicados
        );
        return ResponseEntity.ok(response);
    }

}
