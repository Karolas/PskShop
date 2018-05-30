package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.Utilities.MessageHandler;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.*;

@ViewScoped
@Named
public class ModProductFront implements Serializable {

    @Getter
    @Setter
    private LazyDataModel<Product> lazyModel;

    @Inject
    private ProductUtility productUtility;

    @Inject
    MessageHandler messageHandler;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<Product>() {

            @Override
            public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                lazyModel.setRowCount(productUtility.getProductsCountByFilters(filters));
                return productUtility.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
        };
    }

    public void redirectToEditProductEdit(Product product) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(product == null){
                ec.redirect("edit-product.xhtml");
            } else {
                ec.redirect("edit-product.xhtml?productId=" + product.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(){
        productUtility.deleteProduct(this.lazyModel.getRowData());
        this.init();
        this.updateTable();
        messageHandler.addMessage("Successful", "Product was deleted!");
    }

    public void updateTable() {
        RequestContext.getCurrentInstance().update("productsForm:lazyProducts");
    }
}
