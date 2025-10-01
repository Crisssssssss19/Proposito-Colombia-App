package com.procol.procolombia.auth.security.service;

import com.procol.procolombia.auth.dto.Request.PasswordResetRequestDTO;
import com.procol.procolombia.auth.dto.Request.UserRegisterRequestDTO;
import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.auth.entities.Role;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.auth.exception.alreadyexists.EmailAlreadyExistsException;
import com.procol.procolombia.auth.exception.notfound.AccesoNotFoundException;
import com.procol.procolombia.auth.exception.notfound.RoleNotFoundException;
import com.procol.procolombia.auth.repositories.AccesoRepository;
import com.procol.procolombia.auth.repositories.RoleRepository;
import com.procol.procolombia.auth.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserInfoService implements UserDetailsService {

    private final AccesoRepository accesoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    public UserInfoService(
            AccesoRepository accesoRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder encoder,
            RoleRepository roleRepository) {
        this.accesoRepository = accesoRepository;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        logger.debug("loadUserByUsername correo={}", correo);
        Acceso acceso = accesoRepository.findByCorreoAccesoWithRoles(correo)
                .orElseThrow(() -> new AccesoNotFoundException("Acceso no encontrado con correo: " + correo));
        logger.debug("Acceso encontrado -> id={}, correo={}", acceso.getId(), acceso.getCorreoAcceso());
        logger.debug("Usuario asociado -> id={}, nombre={}", acceso.getUsuario().getId(), acceso.getUsuario().getNombresUsuario());

        //extraer roles
        Set<Role> roles = acceso.getUsuario().getRoles();
        logger.debug("Roles asociados al usuario id={} -> {}",
                acceso.getUsuario().getId(),
                roles.stream().map(Role::getNombreRol).toList());

        return new UserInfoDetail(acceso);
    }

    // 🔑 Cambiar clave de acceso
    public void resetPassword(PasswordResetRequestDTO passwordResetRequestDTO) {
        Acceso acceso = accesoRepository.findByCorreoAcceso(passwordResetRequestDTO.correoAcceso())
                .orElseThrow(() -> new UsernameNotFoundException("Acceso no encontrado"));

        acceso.setClaveAcceso(encoder.encode(passwordResetRequestDTO.newPassword()));
        accesoRepository.save(acceso);
    }

    // 🔑 Obtener roles de un usuario
    public List<String> getUserRoles(String correo) {
        Acceso acceso = accesoRepository.findByCorreoAcceso(correo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return acceso.getUsuario().getRoles().stream()
                .map(rol -> rol.getNombreRol().toUpperCase())
                .toList();
    }
    /*
    // 📌 Crear un nuevo acceso (registro)
    public Acceso crearAcceso(UserRegisterRequestDTO requestDTO) {
        // 1. Validar correo único
        if (accesoRepository.existsByCorreoAcceso(requestDTO.correoAcceso())) {
            throw new EmailAlreadyExistsException("Correo ya registrado: " + requestDTO.correoAcceso());
        }

        // 2. Buscar el usuario por idUsuarioRef
        Usuario usuario = usuarioRepository.findById(requestDTO.idUsuarioRef())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con id: " + requestDTO.idUsuarioRef()));

        // 3. Crear el acceso
        Acceso acceso = Acceso.builder()
                .correoAcceso(requestDTO.correoAcceso())
                .claveAcceso(encoder.encode(requestDTO.claveAcceso()))
                .build();

        // 4. Asignar roles al usuario (no al acceso)
        Set<String> strRoles = requestDTO.roles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role defaultRole = roleRepository.findByNombreRol("USER")
                    .orElseThrow(() -> new RoleNotFoundException("Error: Rol USER no existe"));
            roles.add(defaultRole);
        } else {
            strRoles.forEach(roleName -> {
                Role rol = roleRepository.findByNombreRol(roleName.toUpperCase())
                        .orElseThrow(() -> new RoleNotFoundException("Error: Rol " + roleName + " no existe"));
                roles.add(rol);
            });
        }

        usuario.setRoles(roles);

        // 5. Guardar ambos
        usuarioRepository.save(usuario);
        return accesoRepository.save(acceso);
    }

    public Integer getUsuarioIdByCorreo(String correo) {
        Acceso acceso = accesoRepository.findByCorreoAcceso(correo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getAcceso().getId().equals(acceso.getId()))
                .map(Usuario::getId)
                .findFirst()
                .orElse(null);
    }
     */
}
