package com.procol.procolombia.vacante.Controllers;

import com.procol.procolombia.vacante.Services.EmpresaService;
import com.procol.procolombia.vacante.dto.EmpresaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;

@RestController
@RequestMapping("/Empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Integer> getCantidadEmpresas() {
        Integer cantidad = empresaService.obtenerCantidadEmpresas();
        return ResponseEntity.ok(cantidad);
    }
    @GetMapping
    public ResponseEntity<List<EmpresaDto>> getAllEmpresas() {
        List<EmpresaDto> empresas = empresaService.getAllEmpresas();
        return ResponseEntity.ok(empresas);
    }
    @GetMapping("/buscar")
    public ResponseEntity<Page<EmpresaDto>> buscarEmpresas(
            @RequestParam(required = false, defaultValue = "") String filtro,
            Pageable pageable) {
        Page<EmpresaDto> empresas = empresaService.buscarEmpresasFiltro(filtro, pageable);
        return ResponseEntity.ok(empresas);
    }
    @PostMapping
    public ResponseEntity<EmpresaDto> createEmpresa(@RequestBody EmpresaDto empresaDto) {
        EmpresaDto created = empresaService.createEmpresa(empresaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> updateEmpresa(
            @PathVariable Integer id,
            @RequestBody EmpresaDto empresaDto) {

        EmpresaDto dtoConId = new EmpresaDto(
                id,
                empresaDto.idTipoEmpresa(),
                empresaDto.nombreEmpresa(),
                empresaDto.direccionEmpresa(),
                empresaDto.telefonoEmpresa(),
                empresaDto.interes(),
                empresaDto.relUsuarioEmpresa()
        );

        EmpresaDto updated = empresaService.updateEmpresa(dtoConId);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteEmpresa(@RequestBody EmpresaDto empresaDto) {
        empresaService.deleteEmpresa(empresaDto);
        return ResponseEntity.noContent().build();
    }
}
