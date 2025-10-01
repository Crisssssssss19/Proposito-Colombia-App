package com.procol.procolombia.auth.service;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.RolResponseDTO;

import java.util.List;

public interface RolService {
    ApiResponseDTO<List<RolResponseDTO>> listarRols();
}
