package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.RelUsuarioEmpresaService;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relUsuarioEmpresa")
public class RelUsuarioEmpresaController {
    private final RelUsuarioEmpresaService relUsuarioEmpresaService;

    public RelUsuarioEmpresaController(RelUsuarioEmpresaService relUsuarioEmpresaService) {
        this.relUsuarioEmpresaService = relUsuarioEmpresaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RelUsuarioEmpresaDto>>> getAllUsuariosEmpresas() {
        List<RelUsuarioEmpresaDto> lista = relUsuarioEmpresaService.getAllUsuariosEmpresas();
        ApiResponse<List<RelUsuarioEmpresaDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                lista
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<RelUsuarioEmpresaDto>> getByUsuarioId(@PathVariable Integer usuarioId) {
        RelUsuarioEmpresaDto dto = relUsuarioEmpresaService.findUsuarioEmpresasByUsuarioId(usuarioId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<RelUsuarioEmpresaDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                dto
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RelUsuarioEmpresaDto>> createRelUsuarioEmpresa(@RequestBody RelUsuarioEmpresaDto relUsuarioEmpresaDto) {
        RelUsuarioEmpresaDto created = relUsuarioEmpresaService.createRelUsuarioEmpresa(relUsuarioEmpresaDto);
        ApiResponse<RelUsuarioEmpresaDto> response = new ApiResponse<>(
                200,
                "Creación de relación exitosa",
                created
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<ApiResponse<RelUsuarioEmpresaDto>> getByEmpresaId(@PathVariable Integer empresaId) {
        RelUsuarioEmpresaDto dto = relUsuarioEmpresaService.findUsuarioEmpresaByEmpresaId(empresaId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<RelUsuarioEmpresaDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                dto
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/usuario/{usuarioId}/empresa/{empresaId}")
    public ResponseEntity<ApiResponse<Void>> deleteByUsuarioAndEmpresa(
            @PathVariable Integer usuarioId,
            @PathVariable Integer empresaId) {
        relUsuarioEmpresaService.deleteUsuarioEmpresasByUsuarioId(usuarioId, empresaId);
        ApiResponse<Void> response = new ApiResponse<>(
                200,
                "Relacion eliminada exitosamente",
                null
        );
        return ResponseEntity.noContent().build();
    }


}
