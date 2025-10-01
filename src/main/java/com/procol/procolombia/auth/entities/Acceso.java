package com.procol.procolombia.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mapstruct.Builder;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "accesos")
public class Acceso {

    @Id
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Size(max = 150)
    //@NotNull
    @Column(name = "telefono_acceso", nullable = false, length = 150)
    private String telefonoAcceso;

    @Email
    @Size(max = 150)
    @NotNull
    @Column(name = "correo_acceso", nullable = false, length = 150)
    private String correoAcceso;

    @NotNull
    @Column(name = "correo_verificado", nullable = false)
    private Short correoVerificado = 2; // 1=verificado, 2=no verificado

    @Size(max = 150)
    @NotNull
    @Column(name = "clave_acceso", nullable = false, length = 150)
    private String claveAcceso;

    @Size(max = 150)
    @NotNull
    @Column(name = "uuid_acceso", nullable = false, length = 150)
    private String uuidAcceso;

    @OneToMany(mappedBy = "idUsuario")
    private Set<Ingreso> ingresos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public Acceso() {
    }

    public void setUsuario(Usuario usuarios) {
        this.usuario = usuarios;
    }

    public Short getCorreoVerificado() {
        return correoVerificado;
    }
    public void setCorreoVerificado(Short correoVerificado) {
        this.correoVerificado = correoVerificado;
    }
    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getUuidAcceso() {
        return uuidAcceso;
    }

    public void setUuidAcceso(String uuidAcceso) {
        this.uuidAcceso = uuidAcceso;
    }

    public Set<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(Set<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public static AccesoBuilder builder(){
        return new AccesoBuilder();
    }

    private Acceso(AccesoBuilder builder){
        this.id = builder.id;
        this.usuario = builder.usuario;
        this.telefonoAcceso = builder.telefonoAcceso;
        this.correoAcceso = builder.correoAcceso;
        this.claveAcceso = builder.claveAcceso;
        this.uuidAcceso = builder.uuidAcceso;
        this.ingresos = builder.ingresos;
    }

    public static class AccesoBuilder{
        private Integer id;
        private Usuario usuario;
        private String telefonoAcceso;
        private String correoAcceso;
        private String claveAcceso;
        private String uuidAcceso;
        private Set<Ingreso> ingresos = new LinkedHashSet<>();

        public AccesoBuilder id(Integer id){
            this.id = id;
            return this;
        }
        public AccesoBuilder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }
        public AccesoBuilder telefonoAcceso(String telefonoAcceso) {
            this.telefonoAcceso = telefonoAcceso;
            return this;
        }
        public AccesoBuilder correoAcceso(String correoAcceso) {
            this.correoAcceso = correoAcceso;
            return this;
        }
        public AccesoBuilder claveAcceso(String claveAcceso) {
            this.claveAcceso = claveAcceso;
            return this;
        }
        public AccesoBuilder uuidAcceso(String uuidAcceso) {
            this.uuidAcceso = uuidAcceso;
            return this;
        }
        public AccesoBuilder ingresos(Set<Ingreso> ingresos) {
            this.ingresos = ingresos;
            return this;
        }
        public AccesoBuilder addIngreso(Ingreso ingreso) {
            this.ingresos.add(ingreso);
            return this;
        }

        public Acceso build() {
            return new Acceso(this);
        }
    }
}
