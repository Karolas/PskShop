package psk.database.dao;

import psk.database.entities.Cart;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;
import psk.database.primaryKeys.CartProductsPK;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@RequestScoped
@Named
public class CartProductDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public CartProducts selectCartProduct(Cart cart, Product product) {
        CartProductsPK cartProductsPK = new CartProductsPK();
        cartProductsPK.setProductId(product.getId());
        cartProductsPK.setCartId(cart.getId());

        return em.find(CartProducts.class, cartProductsPK);
    }

    @Transactional
    public List<CartProducts> selectCartProductsByCartId(Integer cartId) {
       return em.createQuery("SELECT cp FROM CartProducts cp WHERE cp.cartId = :cartId", CartProducts.class)
             .setParameter("cartId", cartId).getResultList();
    }

    @Transactional
    public void insertCartProduct(Cart cart, Product product, Integer amount) {
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

    @Transactional
    public boolean isProductInCart(Cart cart, Product product) {
        CartProductsPK cartProductsPK = new CartProductsPK();
        cartProductsPK.setCartId(cart.getId());
        cartProductsPK.setProductId(product.getId());

        long count =  (long)em.createQuery("SELECT COUNT(1) FROM CartProducts cp WHERE cp.id = :id")
                            .setParameter("id", cartProductsPK)
                            .getSingleResult();

        return count > 0;
    }

    @Transactional
    public void updateAmount(CartProducts cartProduct, Integer amount) {
        em.find(CartProducts.class, cartProduct.getId()).setAmount(amount);
    }

    @Transactional
    public void deleteCartProduct(CartProducts cartProduct) {
        CartProducts cartProducts = em.find(CartProducts.class, cartProduct.getId());

        em.remove(cartProducts);
    }

    @Transactional
    public void removeProduct(Integer id){
        em.createQuery("DELETE FROM CartProducts WHERE product.id = :productId").setParameter("productId", id).executeUpdate();
    }
}
