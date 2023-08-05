package com.diego.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orden")
public class Ordenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime fechaCreacion;
    private String estado; //Estado actual de la orden (pendiente, completada, etc.)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(targetEntity = OrdenesProductoNN.class, mappedBy = "ordenes")
    private List<OrdenesProductoNN> detalleOrdenes;

}
