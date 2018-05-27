package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import psk.businessLogic.ModAcountUtility;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.dao.ProductDAO;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class ModProductFront implements Serializable {

    private byte[] bytes;
    @Getter
    @Setter
    private LazyDataModel<Product> lazyModel;

    @Inject
    private ProductUtility productUtility;

    @Getter
    @Setter
    private Product selectedProduct;

    @Getter
    @Setter
    private UploadedFile uploadedFile;

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
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if(params.size() > 0){
            Integer productId = new Integer(params.get("productId"));
            if(productId == 0){
                selectedProduct = new Product();
            } else {
                selectedProduct = productUtility.getProduct(productId);
            }
        }
    }

    public void select(boolean isNew){
        if(isNew)  {
            selectedProduct = new Product();
        } else {
            selectedProduct = this.lazyModel.getRowData();
        }
    }

    public void redirectToEditProductEdit(Product product) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(product == null){
                ec.redirect("edit-product.xhtml?productId=0");
            } else {
                ec.redirect("edit-product.xhtml?productId=" + product.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileUpload(FileUploadEvent event) throws IOException {
        InputStream is = event.getFile().getInputstream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();//FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0)
            out.write(buf, 0, len);
        bytes = out.toByteArray();
        is.close();
        out.close();
    }

    public void updateProduct() {
        selectedProduct.setImage(bytes);
        productUtility.updateProduct(selectedProduct);
    }

    public void createProduct() {
        productUtility.createProduct(selectedProduct, bytes);
    }

    public void deleteProduct(){
        selectedProduct = this.lazyModel.getRowData();
        productUtility.deleteProduct(selectedProduct);
        this.init();
        this.updateTable();
    }

    public void updateTable() {
        RequestContext.getCurrentInstance().update("productsForm:lazyProducts");
    }
}
