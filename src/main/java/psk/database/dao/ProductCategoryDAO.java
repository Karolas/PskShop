package psk.database.dao;

import psk.database.entities.ProductCategory;
import psk.database.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@RequestScoped
@Named
public class ProductCategoryDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Transactional
    public List<ProductCategory> getAll(){
        return em.createQuery("SELECT c from ProductCategory c").getResultList();
    }

    @Transactional
    public List<ProductCategory> getAllCategories(){
        return em.createQuery("SELECT c from ProductCategory c WHERE parentCategory.id is null").getResultList();
    }

    @Transactional
    public List<ProductCategory> getSubCategorieByParent(Integer parentId){
        return em.createQuery("SELECT c from ProductCategory c WHERE parentCategory.id = :parentId")
                .setParameter("parentId", parentId).getResultList();
    }

    @Transactional
    public ProductCategory getCategoryById(Integer id){
        return em.createQuery("SELECT c from ProductCategory c WHERE c.id = :id", ProductCategory.class)
                .setParameter("id", id).getSingleResult();
    }

    @Transactional
    public ProductCategory getCategoryByName(String name){
        List<ProductCategory> results = em.createQuery("SELECT c from ProductCategory c WHERE c.name = :name", ProductCategory.class)
                .setParameter("name", name).getResultList();
        if(!results.isEmpty()) return results.get(0);
        return null;
    }

    @Transactional
    public void insertCategory(ProductCategory productCategory){
        em.persist(productCategory);
    }


    @Transactional
    public void deleteById(Integer id){
        em.createQuery("UPDATE Product p SET p.category = null WHERE p.category.id = :id")
                .setParameter("id", id).executeUpdate();
        em.createQuery("UPDATE ProductCategory c SET c.parentCategory = null " +
                "WHERE c.parentCategory.id = :parentId").setParameter("parentId", id).executeUpdate();
        em.createQuery("DELETE FROM ProductCategory c where c.id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
