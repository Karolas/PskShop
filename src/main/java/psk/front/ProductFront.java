package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.entities.Product;

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

    @Getter
    @Setter
    private String searchString = "";

    @Getter
    @Setter
    private LazyDataModel<Product> products;

    @Getter
    @Setter
    private int productAmount;

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

    public void redirectToProductView(Product product) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect("productView.xhtml?productId=" + product.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
