package com.diego.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private double precio;

    @OneToMany(targetEntity = CarritoProductoNN.class, mappedBy = "producto")
    private List<CarritoProductoNN> detalleProductoCarrito;
    @OneToMany(targetEntity = OrdenesProductoNN.class, mappedBy = "producto")
    private List<OrdenesProductoNN> detalleProductoOrdenes;
}
