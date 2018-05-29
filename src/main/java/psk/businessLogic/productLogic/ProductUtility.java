package psk.businessLogic.productLogic;

import org.primefaces.model.SortOrder;
import psk.database.dao.ProductDAO;
import psk.database.dao.ProductImageDAO;
import psk.database.entities.Product;
import psk.database.entities.ProductImage;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class ProductUtility implements Serializable {
    @Inject
    private ProductDAO productDAO;

    @Inject
    private ProductImageDAO productImageDAO;

    @Inject
    private ImageProvider imageProvider;

    @Transactional
    public Product getProduct(Integer productId) {
        return productDAO.selectProductById(productId);
    }

//    public byte[] getImage(Integer productId) {
//        return productDAO.selectProductById(productId).getImage();
//    }

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

    public void addImageToProduct(Product product, byte[] image) {

        ProductImage productImage = productImageDAO.createProductImage(product.getId());

        imageProvider.saveImage(image, productImage.getId());
    }

    public void addMainImageToProduct(Product product, byte[] image) {

        ProductImage productImage = productImageDAO.createProductImage(product.getId());

        imageProvider.saveImage(image, productImage.getId());

        product.setMainImageId(productImage.getId());
        updateProduct(product);
    }

    public void removeImageFromProduct(ProductImage productImage) {
        productImageDAO.removeProductImage(productImage);
    }

    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product.getId());
    }
}
