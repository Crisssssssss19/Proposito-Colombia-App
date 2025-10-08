package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.TipoEmpresaService;
import com.procol.procolombia.vacante.dto.TipoEmpresaDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/tiposDeEmpresas")
public class TipoEmpresaController {
    private final TipoEmpresaService tipoEmpresaService;

    public TipoEmpresaController(TipoEmpresaService tipoEmpresaService) {
        this.tipoEmpresaService = tipoEmpresaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoEmpresaDto>> getTipoEmpresaById(@PathVariable Integer id) {
        TipoEmpresaDto tipoEmpresa = tipoEmpresaService.findTipoEmpresaById(id);

        ApiResponse<TipoEmpresaDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                tipoEmpresa
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoEmpresaDto>> updateTipoEmpresa(
            @PathVariable Integer id,
            @Valid @RequestBody TipoEmpresaDto tipoEmpresaDto) {
        TipoEmpresaDto updated = tipoEmpresaService.updateTipoEmpresa(id, tipoEmpresaDto);
        ApiResponse<TipoEmpresaDto> response = new ApiResponse<>(
                200,
                "Actualización exitosa",
                updated
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> deleteTipoEmpresa(@PathVariable Integer id) {
        tipoEmpresaService.deleteTipoEmpresa(id);
        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Tipo de empresa eliminado exitosamente",
                id
        );
        return ResponseEntity.ok(response);
    }


}
