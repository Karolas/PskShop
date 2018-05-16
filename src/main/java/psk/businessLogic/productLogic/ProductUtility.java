package psk.businessLogic.productLogic;

import org.primefaces.model.SortOrder;
import psk.database.dao.ProductDAO;
import psk.database.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class ProductUtility implements Serializable {
    @Inject
    private ProductDAO productDAO;

    public byte[] getImage(Integer productId) {
        return productDAO.selectProductById(productId).getImage();
    }

    public int getProductsCount(String nameCriteria) {
        return productDAO.count(nameCriteria);
    }

    public List<Product> getProductsPage(int first, int pageSize, String nameCriteria) {
        return productDAO.getResultList(first, pageSize, nameCriteria);
    }

    public int getProductsCountByFilters(Map<String, Object> filters){
        return productDAO.countWithilters(filters);
    }
    public List<Product> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return productDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
    }
    public void updateProduct(Product product){
        productDAO.updateProduct(product);
    }
    public void createProduct(Product product) {
        productDAO.insertProduct(product);
    }
    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product.getId());
    }
}
