package psk.database.dao;


import psk.database.entities.Order;
import psk.database.entities.OrderProduct;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class OrderProductDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public void insertOrderProduct(OrderProduct orderProduct) { em.persist(orderProduct); }

    @Transactional
    public List<OrderProduct> selectProductsForOrder(Integer orderId) {
        return em.createQuery("SELECT op FROM OrderProduct op WHERE op.order = :orderId")
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
