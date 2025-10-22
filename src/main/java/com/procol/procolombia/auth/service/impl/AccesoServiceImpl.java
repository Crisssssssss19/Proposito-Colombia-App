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
import com.procol.procolombia.auth.mappers.AccesoMapper;
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
    private final String sendGridApiKey;
    private final String sendGridFromEmail;
    private final ParameterNamesModule parameterNamesModule;
    private final UbicacioneRepository ubicacioneRepository;
    private final UsuariosRoleRepository usuariosRoleRepository;
    private final IngresoRepository ingresoRepository;

    public AccesoServiceImpl(AccesoRepository accesoRepository, @Value("${sendgrid.api.key}") String sendGridApiKey, @Value("${sendgrid.from.email}") String sendGridFromEmail, RoleRepository roleRepository, JwtService jwtService, AccesoMapper accesoMapper, RequisitoRepository requisitoRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserInfoService userInfoService, UsuarioRepository usuarioRepository, ImagenServiceImpl imagenServiceImpl, ImageneRepository imageneRepository, ParameterNamesModule parameterNamesModule, UbicacioneRepository ubicacioneRepository, UsuariosRoleRepository usuariosRoleRepository, IngresoRepository ingresoRepository) {
        this.accesoRepository = accesoRepository;
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
    public ApiResponseDTO<String> enviarVerificarCorreo(String correo) {
        Acceso acceso = accesoRepository.findByCorreoAcceso(correo)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado con correo: " + correo));

        if(acceso.getCorreoVerificado()==1){
            return new ApiResponseDTO<>(200, "Correo ya verificado", null, LocalDateTime.now().toString());
        }

        try{
            Email from = new Email(sendGridFromEmail);
            String subject = "verifica tu correo electrónico";
            Email to = new Email(acceso.getCorreoAcceso());

            String link = "http://localhost:3210/api/accesos/verificar-correo?idUsuario=" + acceso.getUsuario().getId() + "&uuid=" + acceso.getId();
            Content content = new Content("text/html",
                    "<h3>Verifica tu correo</h3>" +
                    "<p>Haz clic en el siguiente enlace para verificar tu correo electrónico:</p>" +
                    "<a href=\"" + link + "\">Verificar Correo</a>");

            Mail mail = new Mail(from, subject, to, content);
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if(response.getStatusCode() >= 200 && response.getStatusCode() < 300){
                return new ApiResponseDTO<>(200, "Correo enviado", "Revisa tu bandeja de entrada", LocalDateTime.now().toString());
            } else {
                return new ApiResponseDTO<>(500, "Error al enviar correo", "Código de estado: " + response.getStatusCode(), LocalDateTime.now().toString());
            }
        } catch (IOException ex) {
            return new ApiResponseDTO<>(500, "Excepcion", ex.getMessage(), LocalDateTime.now().toString());
        }
    }

    @Override
    public ApiResponseDTO<String> verificarCorreo(Integer idUsuario, String UUID) {
        Acceso acceso = accesoRepository.findById(idUsuario)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado con id: " + idUsuario));

        if(!acceso.getUuidAcceso().equals(UUID) || acceso.getUuidAcceso()==null){
            return new ApiResponseDTO<>(400, "UUID inválido", null, LocalDateTime.now().toString());
        }
        acceso.setCorreoVerificado((short) 1);
        accesoRepository.save(acceso);
        return new ApiResponseDTO<>(200, "Correo verificado", null, LocalDateTime.now().toString());
    }


}
