package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.auth.entities.Role;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.notfound.AccesoNotFoundException;
import com.procol.procolombia.auth.mappers.AccesoMapper;
import com.procol.procolombia.auth.repositories.AccesoRepository;
import com.procol.procolombia.auth.security.jwt.JwtService;
import com.procol.procolombia.auth.security.service.UserInfoDetail;
import com.procol.procolombia.auth.security.service.UserInfoService;
import com.procol.procolombia.auth.service.AccesoService;
import com.procol.procolombia.vacante.repositories.RequisitoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccesoServiceImpl implements AccesoService {

    private final AccesoRepository accesoRepository;
    private final AccesoMapper accesoMapper;
    private final RequisitoRepository requisitoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;

    public AccesoServiceImpl(AccesoRepository accesoRepository, JwtService jwtService, AccesoMapper accesoMapper, RequisitoRepository requisitoRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserInfoService userInfoService) {
        this.accesoRepository = accesoRepository;
        this.accesoMapper = accesoMapper;
        this.jwtService = jwtService;
        this.requisitoRepository = requisitoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
    }


    @Override
    public ApiResponseDTO<String> eliminarAcceso(Integer idAcceso) {
        if(!accesoRepository.existsById(idAcceso)){
            throw new AccesoNotFoundException("Acceso no encontrado con id: "+idAcceso);
        }
        accesoRepository.deleteById(idAcceso);
        return new ApiResponseDTO<>(200, "Acceso eliminado con exito", null, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<String> cambiarClave(Integer idUsuario, String clave) {
        return null;
    }

    @Override
    public ApiResponseDTO<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
        // 1. Buscar acceso por correo
        Acceso acceso = accesoRepository.findByCorreoAcceso(requestDTO.correoAcceso())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // 2. Validar clave
        if (!passwordEncoder.matches(requestDTO.claveAcceso(), acceso.getClaveAcceso())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        Usuario usuario = acceso.getUsuario();

        // 3. Generar token JWT
        String token = jwtService.generateToken(acceso.getCorreoAcceso());

        // 4. Buscar imagen favorita (o devolver "XXX_IMG" si no hay)
        String fotoBase64 = usuario.getImagenes().stream()
                .filter(img -> img.getFavoritaImagen() == 1)
                .map(Imagene::getNombrePrivadoImagen)
                .findFirst()
                .orElse("XXX_IMG");

        // 5. Armar respuesta
        LoginResponseDTO loginResponse = new LoginResponseDTO(
                token,
                fotoBase64,
                jwtService.getExpirationTime()
        );

        return new ApiResponseDTO<>(
                200,
                "Autenticación exitosa",
                loginResponse,
                LocalDateTime.now().toString()
        );
    }


    @Override
    public ApiResponseDTO<AccesoResponseDTO> editarAcceso(Integer idAcceso, AccesoRequestDTO requestDTO) {
        Acceso acceso = accesoRepository.findById(idAcceso)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado"));

        acceso.setCorreoAcceso(requestDTO.correoAcceso());
        acceso.setTelefonoAcceso(requestDTO.telefonoAcceso());

        Acceso accesoGuardado = accesoRepository.save(acceso);
        return new ApiResponseDTO<>(200, "Acceso actualizado con exito", accesoMapper.toDto(acceso), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<List<AccesoResponseDTO>> ListarAcceso() {
        List<AccesoResponseDTO> listaAccesos = accesoRepository.findAll()
                .stream()
                .map(accesoMapper::toDto)
                .toList();

        return new ApiResponseDTO<>(200, "Accesos encontrados: "+ listaAccesos.size(), listaAccesos, LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<AccesoResponseDTO> crearAcceso(AccesoRequestDTO requestDTO) {
        Acceso acceso = accesoMapper.toEntity(requestDTO);
        Acceso accesoGuardado = accesoRepository.save(acceso);
        return new ApiResponseDTO<>(201, "Creado exitosamente", accesoMapper.toDto(accesoGuardado), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorId(Integer idAcceso) {
        Acceso acceso = accesoRepository.findById(idAcceso)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado"));

        return new ApiResponseDTO<>(200, "Acceso encontrado", accesoMapper.toDto(acceso), LocalDateTime.now().toString());
    }
}
