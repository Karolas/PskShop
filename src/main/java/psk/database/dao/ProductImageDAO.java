package psk.database.dao;

import psk.database.entities.ProductImage;
import psk.database.resources.AsyncComp;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@RequestScoped
@Named
public class ProductImageDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public ProductImage createProductImage(Integer productId) {
        ProductImage productImage = new ProductImage();
        productImage.setProductId(productId);
        em.persist(productImage);

        return productImage;
    }

    @Transactional
    public void removeProductImage(ProductImage productImage) {
        ProductImage productImage1Db = em.find(ProductImage.class, productImage.getId());

        em.remove(productImage1Db);
    }

    @Transactional
    public void removeProduct(Integer id){
        em.createQuery("DELETE FROM ProductImage WHERE product.id = :productId").setParameter("productId", id).executeUpdate();
    }
}
