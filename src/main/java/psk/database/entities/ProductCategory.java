package psk.database.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "products_categories")
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @JoinColumn(name = "parent_category", referencedColumnName = "ID")
    @ManyToOne
    private ProductCategory parentCategory;
}
