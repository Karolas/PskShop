package psk.database.dao;

import psk.database.entities.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class ProductDAO {
    @Inject
    private EntityManager em;

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
}
