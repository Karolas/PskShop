package psk.front;

import psk.businessLogic.CartUtility;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CartFront implements Serializable {

    @Inject
    private CartUtility cartUtility;

    public List<CartProducts> getCartProducts() {
            return cartUtility.getCartProducts();
    }

    public void addToCart(Product product, Integer amount) {
            cartUtility.addProductToCart(product, amount);
    }
}
