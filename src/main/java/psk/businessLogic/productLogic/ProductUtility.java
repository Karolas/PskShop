package psk.businessLogic.productLogic;

import org.primefaces.model.SortOrder;
import psk.database.entities.Product;
import psk.database.entities.ProductAttribute;
import psk.database.entities.ProductCategory;
import psk.database.entities.ProductImage;

import java.util.List;
import java.util.Map;

public interface ProductUtility {
    Product getProduct(Integer productId);
    void updateProduct(Product product);
    void createProduct(Product product);
    void deleteProduct(Product product);
    int getProductsCount(String nameCriteria, Integer categoryId);
    void addImageToProduct(Product product, byte[] image);
    void addMainImageToProduct(Product product, byte[] image);
    void removeImageFromProduct(ProductImage productImage);
    void addAtrtributeToProduct(Product product, ProductAttribute productAttribute);
    void updateProductAttributeSet(Product product, List<ProductAttribute> productAttributes);
    void removeProductAttribute(ProductAttribute productAttribute);
    List<Product> getProductsPage(int first, int pageSize, String nameCriteria, Integer categoryId);
    int getProductsCountByFilters(Map<String, Object> filters);
    List<Product> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    ProductCategory getProductCategoryById(Integer id);
    List<ProductCategory> getAllProductCategories();
    List<ProductCategory> getAllTopLevelCategories();
    void createCategory(ProductCategory productCategory);
    void deleteCategory(ProductCategory productCategory);
}
