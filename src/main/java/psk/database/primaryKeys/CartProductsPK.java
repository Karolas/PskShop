package psk.database.primaryKeys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CartProductsPK implements Serializable {
    @Column(name = "CART_ID")
    private Integer cartId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;
}
