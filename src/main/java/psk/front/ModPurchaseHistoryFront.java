package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import psk.businessLogic.productLogic.ProductUtility;
import psk.businessLogic.purchaseHistoryLogic.purchaseHistoryUtility;
import psk.database.entities.Order;
import psk.database.entities.OrderProduct;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class ModPurchaseHistoryFront {

    private byte[] bytes;
    @Getter
    @Setter
    private LazyDataModel<Order> lazyModel;

    @Inject
    private purchaseHistoryUtility purchaseHistoryUtility;

    @Getter
    @Setter
    private Order selectedOrder;

    @Getter
    @Setter
    private UploadedFile uploadedFile;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<Order>() {

            @Override
            public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                lazyModel.setRowCount(purchaseHistoryUtility.getProductsCountByFilters(filters));
                return purchaseHistoryUtility.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
        };
        lazyModel.setRowCount(purchaseHistoryUtility.getProductsCountByFilters(new HashMap<String, Object>()));
    }

    public void select() {
        selectedOrder = this.lazyModel.getRowData();
        selectedOrder.setStatus("Completed");
        purchaseHistoryUtility.updateOrder(selectedOrder);
        this.updateTable();
    }

//    public void updateProduct() {
//        selectedProduct.setImage(bytes);
//        productUtility.updateProduct(selectedProduct);
//    }
//
//    public void createProduct() {
//        selectedProduct.setImage(bytes);
//        productUtility.createProduct(selectedProduct);
//    }
//
//    public void deleteProduct(){
//        selectedProduct = this.lazyModel.getRowData();
//        productUtility.deleteProduct(selectedProduct);
//        this.init();
//        this.updateTable();
//    }

    public void updateTable() {
        RequestContext.getCurrentInstance().update("purchaseHistoryForm:lazyPurchaseHistoryItems");
    }
}
