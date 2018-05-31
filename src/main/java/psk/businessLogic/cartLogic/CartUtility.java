package psk.businessLogic.cartLogic;

import psk.database.entities.CartProducts;
import psk.database.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface CartUtility {
    BigDecimal getCartFullPrice();
    List<CartProducts> getCartProducts();
    void addProductToCart(Product product, Integer amount);
    void removeFromCart(CartProducts cartProduct);
    void createCart(Integer accountId);
    void mergeCart(List<CartProducts> cartProducts);
    void clearCart();
}
