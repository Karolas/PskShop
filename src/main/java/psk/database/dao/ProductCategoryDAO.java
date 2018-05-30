package psk.database.dao;

import psk.database.entities.ProductCategory;
import psk.database.entities.ProductImage;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ProductCategoryDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public List<ProductCategory> getAllCategories(){
        return em.createQuery("SELECT c from ProductCategory c WHERE parentCategory.id is null").getResultList();
    }

    @Transactional
    public List<ProductCategory> getSubCategorieByParent(Integer parentId){
        return em.createQuery("SELECT c from ProductCategory c WHERE parentCategory.id = :parentId").setParameter("parentId", parentId).getResultList();
    }

    @Transactional
    public ProductCategory getCategoryById(Integer id){
        return em.createQuery("SELECT c from ProductCategory c WHERE c.id = :id", ProductCategory.class)
                .setParameter("id", id).getSingleResult();
    }

}
