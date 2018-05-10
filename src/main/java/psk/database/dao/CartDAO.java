package psk.database.dao;


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
    public void insertProduct(Cart cart) {
        em.persist(cart);
    }
}
