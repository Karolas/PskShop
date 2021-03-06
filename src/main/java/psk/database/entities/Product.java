package psk.database.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "main_image_id")
    private Integer mainImageId;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @JoinColumn(name = "category", referencedColumnName = "ID")
    @ManyToOne
    private ProductCategory category;

    @OneToMany(mappedBy = "product",
               fetch = FetchType.EAGER)
    private Set<ProductImage> productImageList;

    @OneToMany(mappedBy = "product",
               fetch = FetchType.EAGER)
    private Set<ProductAttribute> productAttributeList;
}
