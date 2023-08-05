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
@Table(name = "carrito_compras")
public class CarritoCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(targetEntity = CarritoProductoNN.class, mappedBy = "carritoCompras")
    private List<CarritoProductoNN> carritoProductos;

    /*
    * En la clase CarritoCompras, está utilizando la anotación @ManyToOne para mapear la
    * relación con Usuario. Podría considerar cambiar el nombre del campo idUsuario a simplemente
    *  usuario para mejorar la claridad del código. El nombre actual puede llevar a confusiones,
    *  ya que sugiere que el campo contiene solo el ID del usuario, cuando en realidad contiene
    * una referencia al objeto Usuario completo.*/


}
