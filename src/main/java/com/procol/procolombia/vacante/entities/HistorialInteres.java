package com.procol.procolombia.vacante.entities;

import com.procol.procolombia.auth.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name= "historial_intereses")
public class HistorialInteres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorialInteres;

    @NotNull
    @Column(name = "tipo_interes", nullable = false)
    private Short tipoInteres;

    @Column(name = "fecha_interes", nullable = false, updatable = false)
    private Instant fechaInteres = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false),
            @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    })
    private Interes interes;


}
