package psk.front;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import psk.businessLogic.cartLogic.CartUtility;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
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

    public void removeFromCart(CartProducts cartProduct) {
        cartUtility.removeFromCart(cartProduct);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cart.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
