package com.diego.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrito_producto")
public class CarritoProductoNN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long cantidad;
    @ManyToOne
    @JoinColumn(name = "id_carrito_compras")
    private CarritoCompras carritoCompras;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
