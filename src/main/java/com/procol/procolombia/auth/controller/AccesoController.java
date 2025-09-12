package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.service.AccesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AccesoController {

    private final AccesoService accesoService;
    public AccesoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

}
