package com.diego.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ordenes_producto")
public class OrdenesProductoNN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long cantidad;
    private double precioUnitario;
    @ManyToOne
    @JoinColumn(name = "id_ordenes")
    private Ordenes ordenes;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
