package psk.database.dao;

import org.primefaces.model.SortOrder;
import psk.Utils;
import psk.database.entities.Account;
import psk.database.entities.Product;

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
public class ProductDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Inject
    private Utils utils;

    @Transactional
    public void insertProduct(Product product) {
        em.persist(product);
    }

    @Transactional
    public Product selectProductById(Integer id) {
        return em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void updateProduct(Product product) {
        Product product1 = em.find(Product.class, product.getId());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setAmount(product.getAmount());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
    }

    @Transactional
    public void deleteProduct(Integer id) {
        em.createQuery("DELETE FROM Product p WHERE p.id = :id")
            .setParameter("id", id);
    }

    @Transactional
    public List<Product> selectAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Transactional
    public List<Product> getResultList(int first, int pageSize, String productNameCriteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> myObj = cq.from(Product.class);

        Predicate condition = cb.like(myObj.get("name"), "%" + productNameCriteria + "%");

        cq.where(condition);

        return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Transactional
    public int count(String productNameCriteria) {
        long count = em.createQuery("SELECT COUNT(1) FROM Product p WHERE p.name like :name", Long.class)
                .setParameter("name", "%" + productNameCriteria + "%").getSingleResult();


        return (int)count;
    }


    public int countWithilters(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> myObj = cq.from(Product.class);
        Predicate filterCondition = this.getFilterCondition(cb, myObj, filters);
        if(filterCondition.getExpressions().size() != 0) {
            cq.where(filterCondition);
        }
        cq.select(cb.count(myObj));
        int a = em.createQuery(cq).getSingleResult().intValue();
        return a;
    }

    private Predicate getFilterCondition(CriteriaBuilder cb, Root<Product> myObj, Map<String, Object> filters) {
        return utils.getFilterCondition(cb, myObj, filters);
    }

    public List<Product> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> myObj = cq.from(Product.class);
        Predicate filterCondition = this.getFilterCondition(cb, myObj, filters);
        if(filterCondition.getExpressions().size() != 0) {
            cq.where(filterCondition);
        }
        cq = utils.getSortOrder(cq, cb, myObj, sortOrder, sortField);
        return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }
}
