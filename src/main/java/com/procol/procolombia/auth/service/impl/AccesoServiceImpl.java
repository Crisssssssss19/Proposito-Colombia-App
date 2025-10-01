package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Request.UserRegisterRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.dto.Response.UserRegisterResponseDTO;
import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.auth.entities.Role;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.alreadyexists.EmailAlreadyExistsException;
import com.procol.procolombia.auth.exception.notfound.AccesoNotFoundException;
import com.procol.procolombia.auth.exception.notfound.RoleNotFoundException;
import com.procol.procolombia.auth.mappers.AccesoMapper;
import com.procol.procolombia.auth.repositories.AccesoRepository;
import com.procol.procolombia.auth.repositories.ImageneRepository;
import com.procol.procolombia.auth.repositories.RoleRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import com.procol.procolombia.auth.security.jwt.JwtService;
import com.procol.procolombia.auth.security.service.UserInfoDetail;
import com.procol.procolombia.auth.security.service.UserInfoService;
import com.procol.procolombia.auth.service.AccesoService;
import com.procol.procolombia.vacante.repositories.RequisitoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AccesoServiceImpl implements AccesoService {

    private final AccesoRepository accesoRepository;
    private final AccesoMapper accesoMapper;
    private final RequisitoRepository requisitoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;
    private static final Logger logger = LoggerFactory.getLogger(AccesoServiceImpl.class);
    private final UsuarioRepository usuarioRepository;
    private final ImagenServiceImpl imagenServiceImpl;
    private final ImageneRepository imageneRepository;
    private final RoleRepository roleRepository;

    public AccesoServiceImpl(AccesoRepository accesoRepository, RoleRepository roleRepository, JwtService jwtService, AccesoMapper accesoMapper, RequisitoRepository requisitoRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserInfoService userInfoService, UsuarioRepository usuarioRepository, ImagenServiceImpl imagenServiceImpl, ImageneRepository imageneRepository) {
        this.accesoRepository = accesoRepository;
        this.roleRepository = roleRepository;
        this.accesoMapper = accesoMapper;
        this.jwtService = jwtService;
        this.requisitoRepository = requisitoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
        this.usuarioRepository = usuarioRepository;
        this.imagenServiceImpl = imagenServiceImpl;
        this.imageneRepository = imageneRepository;
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
    @Transactional(readOnly = true)
    public ApiResponseDTO<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
        logger.debug("Login request para correo={}", requestDTO.correoAcceso());
        // 1️⃣ Autenticación con Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.correoAcceso(),
                        requestDTO.claveAcceso()
                )
        );
        logger.debug("Authentication result authenticated={} for principal={}", authentication.isAuthenticated(), requestDTO.correoAcceso());

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Credenciales inválidas");
        }
        // 3️⃣ Obtener roles del usuario autenticado
        List<String> roles = userInfoService.getUserRoles(requestDTO.correoAcceso());
        logger.debug("Roles para {} => {}", requestDTO.correoAcceso(), roles);

        // 2️⃣ Generar token JWT
        String token = jwtService.generateToken(requestDTO.correoAcceso(), roles);
        logger.debug("Token generado (masked) for {} => {}...", requestDTO.correoAcceso(), token != null ? token.substring(0, 8) : "null");

        // 4️⃣ Obtener foto de perfil favorita (si existe)
        Acceso acceso = accesoRepository.findByCorreoAcceso(requestDTO.correoAcceso())
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado"));
        Usuario usuario = acceso.getUsuario();
        String fotoBase64 = imagenServiceImpl.obtenerFotoBase64(acceso.getUsuario().getId());
        if(fotoBase64 == null){
            fotoBase64 = "XXX_IMG"; // Indica que no hay imagen
        }
        logger.debug("Foto favorita encontrada para usuarioId={} : {}", usuario.getId(), fotoBase64.equals("XXX_IMG") ? "NO_ENCONTRADA" : "ENCONTRADA (base64 len=" + fotoBase64.length() + ")");

        // 5️⃣ Armar respuesta DTO
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
    public ApiResponseDTO<UserRegisterResponseDTO> register(UserRegisterRequestDTO userRegisterRequestDTO) {
        // Validar correo único
        if (accesoRepository.existsByCorreoAcceso(userRegisterRequestDTO.correoAcceso())) {
            throw new EmailAlreadyExistsException("Correo ya registrado: " + userRegisterRequestDTO.correoAcceso());
        }

        // Buscar el usuario por documento
        Usuario usuario = usuarioRepository.findByDocumentoUsuario(userRegisterRequestDTO.documentoUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con documento: " + userRegisterRequestDTO.documentoUsuario()));

        // Crear el usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombresUsuario(userRegisterRequestDTO.nombresUsuario());
        nuevoUsuario.setApellidosUsuario(userRegisterRequestDTO.apellidosUsuario());
        nuevoUsuario.setTipoDocumentoUsuario(userRegisterRequestDTO.tipoDocumentoUsuario());
        nuevoUsuario.setDocumentoUsuario(userRegisterRequestDTO.documentoUsuario());
        nuevoUsuario.setEstadoUsuario((short) 1); // Activo por defecto
        nuevoUsuario.setIdUbicacion(usuario.getIdUbicacion()); // Mantener la ubicación existente
        Usuario usuarioGuardado = usuarioRepository.saveAndFlush(nuevoUsuario);
        logger.debug("Usuario guardado: {}", usuarioGuardado);

        // Crear el acceso
        Acceso acceso = Acceso.builder()
                .usuario(usuarioGuardado)
                .correoAcceso(userRegisterRequestDTO.correoAcceso())
                .claveAcceso(passwordEncoder.encode(userRegisterRequestDTO.claveAcceso()))
                .uuidAcceso(UUID.randomUUID().toString())
                .build();
        Acceso accesoGuardado = accesoRepository.saveAndFlush(acceso);
        logger.debug("Nuevo usuario registrado id={} con acceso id={}", usuarioGuardado.getId(), accesoGuardado.getId());

        // Asignar roles al usuario (no al acceso)
        Set<String> strRoles = userRegisterRequestDTO.roles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(roleName -> {
            Role rol = roleRepository.findByNombreRol(roleName.toUpperCase())
                    .orElseThrow(() -> new RoleNotFoundException("Error: Rol " + roleName + " no existe"));
            roles.add(rol);
        });

        usuario.setRoles(roles);

        // 5. Guardar ambos
        usuarioRepository.save(usuario);

        return new ApiResponseDTO<>(201, "Usuario registrado exitosamente", null, LocalDateTime.now().toString());
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

    @Transactional
    @Override
    public ApiResponseDTO<AccesoResponseDTO> crearAcceso(AccesoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.usuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con id: " + requestDTO.usuario()));

        Acceso acceso = new Acceso();
        acceso.setUsuario(usuario);
        acceso.setCorreoAcceso(requestDTO.correoAcceso());
        acceso.setClaveAcceso(passwordEncoder.encode(requestDTO.claveAcceso()));
        acceso.setUuidAcceso(UUID.randomUUID().toString());
        acceso.setTelefonoAcceso(requestDTO.telefonoAcceso() != null ? requestDTO.telefonoAcceso() : "");

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
