package psk.businessLogic.productLogic;

import psk.database.dao.ProductDAO;
import psk.database.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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
}
