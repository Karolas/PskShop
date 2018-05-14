package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.businessLogic.ModAcountUtility;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.dao.ProductDAO;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class ModProductFront implements Serializable {
    @Getter
    @Setter
    private LazyDataModel<Product> lazyModel;

    @Inject
    private ProductDAO productDAO;

    @Inject
    private ProductUtility productUtility;

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

    public void block() {
//        modUserUtility.BlockUser(this.lazyModel.getRowData());
        this.updateTable();
    }

    public void unblock() {
//        modUserUtility.UnblockUser(this.lazyModel.getRowData());
//        this.lazyModel.getRowData().setRole("User");
        this.updateTable();
    }

    public void updateTable() {
        RequestContext.getCurrentInstance().update("usersForm:lazyUsers");
    }
}
