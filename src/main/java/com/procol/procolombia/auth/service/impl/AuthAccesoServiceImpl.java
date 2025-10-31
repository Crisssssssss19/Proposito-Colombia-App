package com.procol.procolombia.auth.service.impl;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Request.UserRegisterRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.dto.Response.UserRegisterResponseDTO;
import com.procol.procolombia.auth.entities.*;
import com.procol.procolombia.auth.exception.alreadyexists.EmailAlreadyExistsException;
import com.procol.procolombia.auth.exception.alreadyexists.UsuarioAlreadyExistsException;
import com.procol.procolombia.auth.exception.notfound.AccesoNotFoundException;
import com.procol.procolombia.auth.exception.notfound.RoleNotFoundException;
import com.procol.procolombia.auth.exception.notfound.UbicacionNotFoundException;
import com.procol.procolombia.auth.mappers.AuthAccesoMapper;
import com.procol.procolombia.auth.repositories.*;
import com.procol.procolombia.auth.security.jwt.JwtService;
import com.procol.procolombia.auth.security.service.UserInfoDetail;
import com.procol.procolombia.auth.security.service.UserInfoService;
import com.procol.procolombia.auth.service.AccesoService;
import com.procol.procolombia.vacante.repositories.RequisitoRepository;
import com.sendgrid.Method;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthAccesoServiceImpl implements AccesoService {

    private final AccesoRepository accesoRepository;
    private final AuthAccesoMapper accesoMapper;
    private final RequisitoRepository requisitoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;
    private static final Logger logger = LoggerFactory.getLogger(AuthAccesoServiceImpl.class);
    private final UsuarioRepository usuarioRepository;
    private final AuthImagenServiceImpl imagenServiceImpl;
    private final ImagenRepository imageneRepository;
    private final RoleRepository roleRepository;
    private final String sendGridApiKey;
    private final String sendGridFromEmail;
    private final ParameterNamesModule parameterNamesModule;
    private final UbicacioneRepository ubicacioneRepository;
    private final UsuariosRoleRepository usuariosRoleRepository;
    private final IngresoRepository ingresoRepository;
    private final CorreoVerificacionRepository verificacionRepository;
    private final PreRegistroRepository preRegistroRepository;

    public AuthAccesoServiceImpl(AccesoRepository accesoRepository, PreRegistroRepository preRegistroRepository, CorreoVerificacionRepository verificacionRepository, @Value("${sendgrid.api.key}") String sendGridApiKey, @Value("${sendgrid.from.email}") String sendGridFromEmail, RoleRepository roleRepository, JwtService jwtService, AuthAccesoMapper accesoMapper, RequisitoRepository requisitoRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserInfoService userInfoService, UsuarioRepository usuarioRepository, AuthImagenServiceImpl imagenServiceImpl, ImagenRepository imageneRepository, ParameterNamesModule parameterNamesModule, UbicacioneRepository ubicacioneRepository, UsuariosRoleRepository usuariosRoleRepository, IngresoRepository ingresoRepository) {
        this.accesoRepository = accesoRepository;
        this.preRegistroRepository = preRegistroRepository;
        this.verificacionRepository = verificacionRepository;
        this.ingresoRepository = ingresoRepository;
        this.sendGridApiKey = sendGridApiKey;
        this.sendGridFromEmail = sendGridFromEmail;
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
        this.parameterNamesModule = parameterNamesModule;
        this.ubicacioneRepository = ubicacioneRepository;
        this.usuariosRoleRepository = usuariosRoleRepository;
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
    @Transactional
    public ApiResponseDTO<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
        logger.debug("Login request para correo={}", requestDTO.correoAcceso());
        // Autenticación con Spring Security
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
        Acceso acceso = accesoRepository.findByCorreoAcceso(requestDTO.correoAcceso())
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado"));
        Usuario usuario = acceso.getUsuario();
        // Obtener roles del usuario autenticado
        List<String> roles = userInfoService.getUserRoles(requestDTO.correoAcceso());
        logger.debug("Roles para {} => {}", requestDTO.correoAcceso(), roles);

        // Generar token JWT
        String token = jwtService.generateToken(requestDTO.correoAcceso(), roles, usuario.getId(), usuario.getNombresUsuario(), usuario.getApellidosUsuario(), acceso.getUuidAcceso());
        logger.debug("Token generado (masked) for {} => {}...", requestDTO.correoAcceso(), token != null ? token.substring(0, 8) : "null");

        // Obtener foto de perfil favorita (si existe)

        String fotoBase64 = imagenServiceImpl.obtenerFotoBase64(acceso.getUsuario().getId());
        if(fotoBase64 == null){
            fotoBase64 = "XXX_IMG"; // Indica que no hay imagen
        }
        logger.debug("Foto favorita encontrada para usuarioId={} : {}", usuario.getId(), fotoBase64.equals("XXX_IMG") ? "NO_ENCONTRADA" : "ENCONTRADA (base64 len=" + fotoBase64.length() + ")");

        LoginResponseDTO loginResponse = new LoginResponseDTO(
                token,
                fotoBase64,
                jwtService.getExpirationTime()
        );

        Ingreso ingreso = new Ingreso();
        ingreso.setIdUsuario(acceso);
        ingreso.setFechaIngreso(LocalDateTime.now());
        ingresoRepository.save(ingreso);

        return new ApiResponseDTO<>(200,"Autenticación exitosa", loginResponse, LocalDateTime.now().toString()
        );
    }

    @Override
    @Transactional
    public ApiResponseDTO<UserRegisterResponseDTO> register(UserRegisterRequestDTO userRegisterRequestDTO) {
        // Validar correo único
        if (accesoRepository.existsByCorreoAcceso(userRegisterRequestDTO.correoAcceso())) {
            throw new EmailAlreadyExistsException("Correo ya registrado: " + userRegisterRequestDTO.correoAcceso());
        }

        // Validar documento único
        if (usuarioRepository.existsByDocumentoUsuario(userRegisterRequestDTO.documentoUsuario())) {
            throw new UsuarioAlreadyExistsException("Documento ya registrado: " + userRegisterRequestDTO.documentoUsuario());
        }

        // Buscar ubicación
        Ubicacione ubicacion = ubicacioneRepository.findById(userRegisterRequestDTO.idUbicacion())
                .orElseThrow(() -> new UbicacionNotFoundException("Ubicacion no encontrada"));

        // Crear el usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombresUsuario(userRegisterRequestDTO.nombresUsuario());
        nuevoUsuario.setApellidosUsuario(userRegisterRequestDTO.apellidosUsuario());
        nuevoUsuario.setTipoDocumentoUsuario(userRegisterRequestDTO.tipoDocumentoUsuario());
        nuevoUsuario.setDocumentoUsuario(userRegisterRequestDTO.documentoUsuario());
        nuevoUsuario.setEstadoUsuario((short) 1); // Activo por defecto
        nuevoUsuario.setIdUbicacion(ubicacion);
        Usuario usuarioGuardado = usuarioRepository.saveAndFlush(nuevoUsuario);
        logger.debug("Nuevo usuario creado: {}", usuarioGuardado);

        // Crear el acceso
        Acceso acceso = Acceso.builder()
                .usuario(usuarioGuardado)
                .correoAcceso(userRegisterRequestDTO.correoAcceso())
                .claveAcceso(passwordEncoder.encode(userRegisterRequestDTO.claveAcceso()))
                .telefonoAcceso(userRegisterRequestDTO.telefonoUsuario())
                .uuidAcceso(UUID.randomUUID().toString())
                .build();
        accesoRepository.saveAndFlush(acceso);
        logger.debug("Nuevo usuario registrado id={}", usuarioGuardado.getId());

        // Asignar roles
        for (String roleName : userRegisterRequestDTO.roles()) {
            Role rol = roleRepository.findByNombreRol(roleName)
                    .orElseThrow(() -> new RoleNotFoundException("Role no encontrado: " + roleName));

            UsuariosRoleId id = new UsuariosRoleId(rol.getId(), usuarioGuardado.getId());
            UsuariosRole usuariosRole = new UsuariosRole();
            usuariosRole.setId(id);
            usuariosRole.setIdRol(rol);
            usuariosRole.setIdUsuario(usuarioGuardado);

            usuariosRoleRepository.save(usuariosRole);
        }

        // Crear CorreoVerificacion asociado al correoAcceso
        CorreoVerificacion correoVerificacion = new CorreoVerificacion();
        correoVerificacion.setIdCorreo(userRegisterRequestDTO.correoAcceso());
        correoVerificacion.setPinCorreo(null); // aún no generado
        correoVerificacion.setEstadoCorreoVerificado((short) 1); // sin verificar
        verificacionRepository.save(correoVerificacion);

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

    @Override
    public ApiResponseDTO<AccesoResponseDTO> obtenerAccesoPorCorreo(String correoAcceso) {
        Acceso acceso = accesoRepository.findByCorreoAcceso(correoAcceso)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado con correo: " + correoAcceso));
        return new ApiResponseDTO<>(200, "Acceso encontrado", accesoMapper.toDto(acceso), LocalDateTime.now().toString());
    }

    @Override
    public ApiResponseDTO<String> obtenerTelefonoAcceso(Integer idUsuario) {
        Acceso acceso = accesoRepository.findById(idUsuario)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado"));
        return new ApiResponseDTO<>(200, "Telefono encontrado", acceso.getTelefonoAcceso(), LocalDateTime.now().toString());
    }

    @Override
    @Transactional
    public ApiResponseDTO<String> actualizarTelefonoAcceso(Integer idUsuario, String nuevoTelefono) {
        LocalDateTime ahora = LocalDateTime.now();

        // 1️⃣ Buscar el acceso por el usuario
        Acceso acceso = accesoRepository.findById(idUsuario)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado para usuario ID: " + idUsuario));

        String telefonoAnterior = acceso.getTelefonoAcceso();

        // 2️⃣ Actualizar el teléfono en Acceso
        acceso.setTelefonoAcceso(nuevoTelefono);
        accesoRepository.save(acceso);

        // 3️⃣ Buscar también en PreRegistro por el teléfono anterior
        Optional<PreRegistro> preExistente = preRegistroRepository.findById(telefonoAnterior);

        if (preExistente.isPresent()) {
            PreRegistro pre = preExistente.get();

            // Si el usuario tenía un registro previo con el teléfono antiguo, lo actualizamos
            preRegistroRepository.delete(pre); // Eliminamos el antiguo

            // Creamos uno nuevo con el nuevo número (manteniendo estado si estaba verificado)
            PreRegistro nuevo = new PreRegistro();
            nuevo.setIdPreRegistro(nuevoTelefono);
            nuevo.setPinPreRegistro(pre.getPinPreRegistro());
            nuevo.setFechaPreRegistro(ahora);
            nuevo.setEstadoPreRegistro(pre.getEstadoPreRegistro());
            nuevo.setIntentos(pre.getIntentos());
            nuevo.setBloqueadoHasta(pre.getBloqueadoHasta());

            preRegistroRepository.save(nuevo);
        }

        return new ApiResponseDTO<>(200, "Teléfono actualizado correctamente", "El número fue cambiado en Acceso y PreRegistro", ahora.toString());
    }
}
