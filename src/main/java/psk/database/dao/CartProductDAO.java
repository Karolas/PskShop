package psk.database.dao;

import psk.database.entities.Cart;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;
import psk.database.primaryKeys.CartProductsPK;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@SessionScoped
@Named
public class CartProductDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public void createLink(Cart cart, Product product, Integer amount) {
        CartProducts cartProducts = new CartProducts();
        CartProductsPK cartProductsPK = new CartProductsPK();

        cartProductsPK.setCartId(cart.getId());
        cartProductsPK.setProductId(product.getId());

        cartProducts.setId(cartProductsPK);
        cartProducts.setCart(cart);
        cartProducts.setProduct(em.find(Product.class, product.getId()));
        cartProducts.setAmount(amount);

        em.persist(cartProducts);
    }
}
