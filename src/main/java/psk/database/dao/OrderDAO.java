package psk.database.dao;


import org.primefaces.model.SortOrder;
import psk.Utilities.Utils;
import psk.database.entities.Account;
import psk.database.entities.Order;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class OrderDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Inject
    Utils utils;

    @Transactional
    public void createOrder(Order order) {
        em.persist(order);
    }

    @Transactional
    public void updateOrder(Order order) {
        Order order1 = getOrderById(order.getId());
        order1.setOrderCreated(order.getOrderCreated());
        order1.setStatus(order.getStatus());
        order1.setProducts(order.getProducts());
    }

    @Transactional
    public Order getOrderById(Integer id) {
        return em.find(Order.class, id);
    }

    @Transactional
    public int count(String productNameCriteria) {
        long count = em.createQuery("SELECT COUNT(1) FROM Product p WHERE p.name like :name", Long.class)
                .setParameter("name", "%" + productNameCriteria + "%").getSingleResult();
        return (int)count;
    }

    public List<Order> getAllOrders(Account account) {
        return em.createQuery("SELECT o FROM  Order o where o.account.id = :accountId")
                .setParameter("accountId", account.getId()).getResultList();
    }

    public int countWithilters(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Order> myObj = cq.from(Order.class);
        Predicate filterCondition = this.getFilterCondition(cb, myObj, filters);
        if(filterCondition.getExpressions().size() != 0) {
            cq.where(filterCondition);
        }
        cq.select(cb.count(myObj));
        int a = em.createQuery(cq).getSingleResult().intValue();
        return a;
    }

    private Predicate getFilterCondition(CriteriaBuilder cb, Root<Order> myObj, Map<String, Object> filters) {
        return utils.getFilterCondition(cb, myObj, filters);
    }

    @Transactional
    public List<Order> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> myObj = cq.from(Order.class);
        Predicate filterCondition = this.getFilterCondition(cb, myObj, filters);
        if(filterCondition.getExpressions().size() != 0) {
            cq.where(filterCondition);
        }
        cq = utils.getSortOrder(cq, cb, myObj, sortOrder, sortField);
        return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

}
