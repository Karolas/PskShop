package psk.businessLogic.productLogic;

import org.primefaces.model.SortOrder;
import psk.InterceptorLog;
import psk.database.dao.ProductAttributeDAO;
import psk.database.dao.ProductCategoryDAO;
import psk.database.dao.ProductDAO;
import psk.database.dao.ProductImageDAO;
import psk.database.entities.Product;
import psk.database.entities.ProductAttribute;
import psk.database.entities.ProductCategory;
import psk.database.entities.ProductImage;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
@RequestScoped
public class ProductUtility implements Serializable {
    @Inject
    private ProductDAO productDAO;

    @Inject
    private ProductCategoryDAO productCategoryDAO;

    @Inject
    private ProductImageDAO productImageDAO;

    @Inject
    private ProductAttributeDAO productAttributeDAO;

    @Inject
    private ImageProvider imageProvider;

    @Transactional
    public Product getProduct(Integer productId) {
        return productDAO.selectProductById(productId);
    }

    @InterceptorLog
    public void updateProduct(Product product){
        productDAO.updateProduct(product);
    }
    @InterceptorLog
    public void createProduct(Product product) {
        productDAO.insertProduct(product);
    }
    @InterceptorLog
    public void deleteProduct(Product product) {
        productImageDAO.removeProduct(product.getId());
        productDAO.deleteProduct(product.getId());
    }

    public int getProductsCount(String nameCriteria, Integer categoryId) {
        int count = 0;
        List<ProductCategory> subCategories = productCategoryDAO.getSubCategorieByParent(categoryId);
        for(int i=0; i<subCategories.size(); i++){
            count += productDAO.count(nameCriteria, subCategories.get(i).getId());
        }
        return productDAO.count(nameCriteria, categoryId) + count;
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

    public void addAtrtributeToProduct(Product product, ProductAttribute productAttribute) {
        productAttribute.setProductId(product.getId());
        productAttribute.setProduct(product);

        productAttributeDAO.createProductAttribute(productAttribute);
    }

    @Transactional
    public void updateProductAttributeSet(Product product, Set<ProductAttribute> productAttributes) {
        Product productDb = getProduct(product.getId());

        for(ProductAttribute productAttribute: productDb.getProductAttributeList()) {
            boolean matchFound = false;
            for(ProductAttribute productAttributeUpdated: productAttributes) {

                if(productAttribute.getId().equals(productAttributeUpdated.getId()))
                    matchFound = true;
            }
            if(!matchFound) productAttributeDAO.deleteProductAttribute(productAttribute);
        }

        for (ProductAttribute productAttribute : productAttributes) {
            if (productAttribute.getId() == null) {
                addAtrtributeToProduct(productDb, productAttribute);
//                productAttributeDAO.createProductAttribute(productAttribute);
//
//                productAttribute.setProduct(productDb);
            }
        }
    }

    public void removeProductAttribute(ProductAttribute productAttribute) {
        productAttributeDAO.deleteProductAttribute(productAttribute);
    }

    public List<Product> getProductsPage(int first, int pageSize, String nameCriteria, Integer categoryId) {
        List<Product> productsList = productDAO.getResultList(first, pageSize, nameCriteria, categoryId);
        List<ProductCategory> subCategories = productCategoryDAO.getSubCategorieByParent(categoryId);
        for(int i=0; i<subCategories.size(); i++){
            productsList = Stream.concat(productsList.stream(), productDAO.getResultList(first, pageSize, nameCriteria,
                    subCategories.get(i).getId()).stream())
                    .collect(Collectors.toList());
        }
        return productsList;
    }

    public int getProductsCountByFilters(Map<String, Object> filters){
        return productDAO.countWithilters(filters);
    }

    public List<Product> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return productDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
    }

    public ProductCategory getProductCategoryById(Integer id){
        return productCategoryDAO.getCategoryById(id);
    }
    public List<ProductCategory> getAllProductCategories(){
        return productCategoryDAO.getAll();
    }
}
