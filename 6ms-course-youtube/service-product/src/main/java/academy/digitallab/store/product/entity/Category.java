package academy.digitallab.store.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
