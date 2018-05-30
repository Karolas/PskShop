package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.Param;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.Utilities.MessageHandler;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.entities.Product;
import psk.database.entities.ProductCategory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class ProductFront implements Serializable {
    @Inject
    private ProductUtility productUtility;

    @Inject
    MessageHandler messageHandler;

    @Getter
    @Setter
    private String searchString = "";

    @Inject @Param(name="categoryId")
    private Integer categoryId;

    @Getter
    @Setter
    private LazyDataModel<Product> products;

    @Getter
    @Setter
    private int productAmount;

    @Getter
    private ProductCategory selectedCategory;

    @PostConstruct
    public void loadProducts() {
        products = new LazyDataModel<Product>() {
            @Override
            public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                products.setRowCount(productUtility.getProductsCount(searchString, categoryId));
                return productUtility.getProductsPage(first, pageSize, searchString, categoryId);
            }
        };
        if(categoryId != null){
            selectedCategory = productUtility.getProductCategoryById(categoryId);
        }
    }

    public void redirectToProductView(Product product) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect("productView.xhtml?productId=" + product.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
