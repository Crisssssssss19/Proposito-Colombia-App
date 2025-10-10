package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.EmpresaService;
import com.procol.procolombia.vacante.dto.EmpresaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<ApiResponse<Integer>> getCantidadEmpresas() {
        Integer cantidad = empresaService.obtenerCantidadEmpresas();

        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Cantidad de registros",
                cantidad
        );

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmpresaDto>>> getAllEmpresas() {
        List<EmpresaDto> empresas = empresaService.getAllEmpresas();

        ApiResponse<List<EmpresaDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                empresas
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<Page<EmpresaDto>>> buscarEmpresas(
            @RequestParam(required = false, defaultValue = "") String filtro,
            Pageable pageable) {
        Page<EmpresaDto> empresas = empresaService.buscarEmpresasFiltro(filtro, pageable);

        ApiResponse<Page<EmpresaDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                empresas
        );
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<EmpresaDto>> createEmpresa(@RequestBody EmpresaDto empresaDto) {
        EmpresaDto created = empresaService.createEmpresa(empresaDto);
        ApiResponse<EmpresaDto> response = new ApiResponse<>(
                201,
                "Empresa creada exitosamente",
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpresaDto>> updateEmpresa(
            @PathVariable Integer id,
            @Valid @RequestBody EmpresaDto empresaDto) {
        EmpresaDto updated = empresaService.updateEmpresa(id, empresaDto);
        ApiResponse<EmpresaDto> response = new ApiResponse<>(
                201,
                "Empresa actualizada exitosamente",
                updated
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<EmpresaDto>> deleteEmpresa(@RequestBody EmpresaDto empresaDto) {
        empresaService.deleteEmpresa(empresaDto);
        ApiResponse<EmpresaDto> response = new ApiResponse<>(
                200,
                "Empresa eliminada exitosamente",
                empresaDto
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipo/{tipoEmpresaId}")
    public ResponseEntity<ApiResponse<List<EmpresaDto>>> getEmpresasByTipoEmpresa(@PathVariable Integer tipoEmpresaId) {
        List<EmpresaDto> empresas = empresaService.getEmpresasByTipoEmpresas(tipoEmpresaId);
        if (empresas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        ApiResponse<List<EmpresaDto>> response = new ApiResponse<>(
                200,
                "Empresa actualizada exitosamente",
                empresas
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ApiResponse<EmpresaDto>> getEmpresaById(@PathVariable Integer id){
        EmpresaDto empresa = empresaService.getEmpresaById(id);
        if(empresa!=null){
            ApiResponse<EmpresaDto> response = new ApiResponse<>(
                    200,
                    "Empresa encontrada por id",
                    empresa
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }
}
