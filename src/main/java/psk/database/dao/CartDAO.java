package psk.database.dao;


import psk.database.entities.Cart;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class CartDAO {
    @Inject
    private EntityManager em;

    @Transactional
    public void insertProduct(Cart cart) {
        em.persist(cart);
    }
}
