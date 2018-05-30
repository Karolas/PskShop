package psk.database.dao;

import psk.database.entities.Product;
import psk.database.entities.ProductAttribute;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ProductAttributeDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public ProductAttribute getProductAttribute(Integer productAttributeId) {
        return em.find(ProductAttribute.class, productAttributeId);
    }

    @Transactional
    public void createProductAttribute(ProductAttribute productAttribute) {
        em.persist(productAttribute);
    }

    @Transactional
    public void updateProductAttribute(ProductAttribute productAttribute) {
        ProductAttribute currentProductAttribute = em.find(ProductAttribute.class, productAttribute.getId());

        currentProductAttribute.setAttributeName(productAttribute.getAttributeName());
        currentProductAttribute.setAttributeDescription(productAttribute.getAttributeDescription());
    }

    @Transactional
    public void deleteProductAttribute(ProductAttribute productAttribute) {
        ProductAttribute currentProductAttribute = em.find(ProductAttribute.class, productAttribute.getId());

        em.remove(currentProductAttribute);
    }
}
