package com.diego.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private LocalDateTime fechaRegistro;

    @OneToMany(targetEntity = CarritoCompras.class, mappedBy = "usuario")
    private List<CarritoCompras> carritosDeCompraDeUsuario;
    @OneToMany(targetEntity = Ordenes.class, mappedBy = "usuario")
    private List<Ordenes> ordenesDelUsuario;

}
