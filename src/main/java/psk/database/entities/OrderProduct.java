package psk.database.entities;

import lombok.Getter;
import lombok.Setter;
import psk.database.primaryKeys.CartProductsPK;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_product")
@Getter
@Setter
public class OrderProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private BigDecimal price;

    @JoinColumn(name = "order_id", referencedColumnName = "ID")
    @ManyToOne
    private Order order;
}
