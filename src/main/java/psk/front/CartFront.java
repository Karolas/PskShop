package psk.front;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import psk.businessLogic.cartLogic.CartUtility;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ViewScoped
@Named
public class CartFront implements Serializable {

    @Inject
    private CartUtility cartUtility;

    public List<CartProducts> getCartProducts() {
        return cartUtility.getCartProducts();
    }

    public void addToCart(Product product) {
        cartUtility.addProductToCart(product, 1);
    }

    public void removeFromCart(CartProducts cartProduct) {
        cartUtility.removeFromCart(cartProduct);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cart.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
