package com.procol.procolombia.auth.security.service;

import com.procol.procolombia.auth.entities.Acceso;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInfoDetail implements UserDetails {

    private final Integer idAcceso;
    private final String correoAcceso;
    private final String claveAcceso;
    private final String telefonoAcceso;
    private final String uuidAcceso;
    private final Set<GrantedAuthority> authorities;

    public UserInfoDetail(Acceso acceso) {
        this.idAcceso = acceso.getId();
        this.correoAcceso = acceso.getCorreoAcceso() != null ? acceso.getCorreoAcceso() : "XXX_MAIL";
        this.claveAcceso = acceso.getClaveAcceso() != null ? acceso.getClaveAcceso() : "XXX_PASS";
        this.telefonoAcceso = acceso.getTelefonoAcceso() != null ? acceso.getTelefonoAcceso() : "XXX_TEL";
        this.uuidAcceso = acceso.getUuidAcceso() != null ? acceso.getUuidAcceso() : "XXX_UUID";

        this.authorities = acceso.getUsuario().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombreRol()))
                .collect(Collectors.toSet());
    }

    public Integer getIdAcceso() {
        return idAcceso;
    }
    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }
    public String getUuidAcceso() {
        return uuidAcceso;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return claveAcceso;
    }

    @Override
    public String getUsername() {
        return correoAcceso; // Usando el correo como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
