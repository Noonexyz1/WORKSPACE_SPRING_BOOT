package com.diego.model;

import com.diego.enums.UserTypeRoles;
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
    @Enumerated(EnumType.STRING)
    private UserTypeRoles rol;

}
