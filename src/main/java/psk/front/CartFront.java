package psk.front;

import psk.Utilities.MessageHandler;
import psk.businessLogic.cartLogic.CartUtility;
import psk.businessLogic.cartLogic.CartUtilityBase;
import psk.database.entities.Cart;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@ViewScoped
@Named
public class CartFront implements Serializable {

    @Inject
    private CartUtility cartUtility;

    @Inject
    MessageHandler messageHandler;

    public Set<CartProducts> getCartProducts() {
        return cartUtility.getCartProducts();
    }

    @Transactional
    public void addToCart(Product product) {
        cartUtility.addProductToCart(product, 1);
        messageHandler.addMessage("Successful", "You have added " + product.getName() + " to your cart!");
    }

    @Transactional
    public String removeFromCart(CartProducts cartProduct) {
        cartUtility.removeFromCart(cartProduct);
        return "cart.xhtml";
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (CartProducts cartProduct: getCartProducts()) {
            BigDecimal productPrice = cartProduct.getProduct().getPrice()
                                        .multiply(new BigDecimal(cartProduct.getAmount()));
            totalPrice = totalPrice.add(productPrice);
        }

        return totalPrice;
    }

    public void redirectToPaymentData(Cart cart) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect("paymentData.xhtml?productId=" + cart.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
