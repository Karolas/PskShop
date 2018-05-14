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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
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
    }

    public void select(){
        selectedProduct = this.lazyModel.getRowData();
    }

    public void upload() {
        System.out.print("Ikeliau");
    }

    public void fileUpload(FileUploadEvent event) throws IOException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
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
}
