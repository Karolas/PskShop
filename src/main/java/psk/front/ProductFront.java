package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class ProductFront implements Serializable {
    @Inject
    private ProductUtility productUtility;

    @Getter
    @Setter
    private String searchString = "";

    @Getter
    @Setter
    private LazyDataModel<Product> products;

    @Getter
    @Setter
    private Product selectedProduct;

    @PostConstruct
    public void loadProducts() {
        products = new LazyDataModel<Product>() {
            @Override
            public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                products.setRowCount(productUtility.getProductsCount(searchString));
                return productUtility.getProductsPage(first, pageSize, searchString);
            }
        };
    }
}
