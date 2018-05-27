package psk.database.dao;


import psk.database.entities.Order;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@SessionScoped
@Named
public class OrderDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public void createOrder(Order order) { em.persist(order); }

    @Transactional
    public void updateProduct(Order order) {
        Order order1 = getOrderById(order.getId());
        order1.setOrderCreated(order.getOrderCreated());
        order1.setStatus(order.getStatus());
        order1.setProducts(order.getProducts());
    }
    @Transactional
    public Order getOrderById(Integer id) {
        return em.find(Order.class, id);
    }
}
