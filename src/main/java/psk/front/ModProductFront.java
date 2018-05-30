package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import psk.businessLogic.ModAcountUtility;
import psk.businessLogic.productLogic.LocalImageProvider;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.dao.ProductDAO;
import psk.database.entities.Product;
import psk.database.entities.ProductImage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@ViewScoped
@Named
public class ModProductFront implements Serializable {

    @Getter
    @Setter
    private LazyDataModel<Product> lazyModel;

    @Inject
    private ProductUtility productUtility;

    @Getter
    @Setter
    private Product selectedProduct;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<Product>() {

            @Override
            public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                lazyModel.setRowCount(productUtility.getProductsCountByFilters(filters));
                return productUtility.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
        };
        lazyModel.setRowCount(productUtility.getProductsCountByFilters(new HashMap<String, Object>()));
    }

    public void select(boolean isNew){
        if(isNew)  {
            selectedProduct = new Product();
        } else {
            selectedProduct = this.lazyModel.getRowData();
        }
    }

    public void redirectToEditProductEdit(Product product, Boolean isButton) {
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
        selectedProduct = this.lazyModel.getRowData();
        productUtility.deleteProduct(selectedProduct);
        this.init();
        this.updateTable();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Product was deleted!") );

    }

    public void updateTable() {
        RequestContext.getCurrentInstance().update("productsForm:lazyProducts");
    }
}
