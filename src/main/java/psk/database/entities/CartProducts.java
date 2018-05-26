package psk.database.entities;

import lombok.Getter;
import lombok.Setter;
import psk.database.primaryKeys.CartProductsPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_products")
@Getter
@Setter
public class CartProducts implements Serializable {

    @EmbeddedId
    private CartProductsPK id;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @MapsId("cartId") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
}
