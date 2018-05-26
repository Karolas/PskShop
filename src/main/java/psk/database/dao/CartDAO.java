package psk.database.dao;


import psk.database.entities.Account;
import psk.database.entities.Cart;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@SessionScoped
@Named
public class CartDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public void createCart(Cart cart) { em.persist(cart); }

    @Transactional
    public Cart getCartById(Integer id) {
        return em.find(Cart.class, id);
    }

    @Transactional
    public Cart getCartByAccId(Integer accountId) {
        return em.createQuery("SELECT c FROM Cart c WHERE c.accountId = :accID", Cart.class)
                .setParameter("accID", accountId)
                .getSingleResult();
    }
}
