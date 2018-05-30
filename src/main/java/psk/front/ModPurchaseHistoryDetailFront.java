package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.Param;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.businessLogic.productLogic.ProductUtility;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
import psk.database.dao.OrderDAO;
import psk.database.entities.*;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@ViewScoped
@Named
public class ModPurchaseHistoryDetailFront implements Serializable {

    @Inject
    @Param(name="orderId")
    private Integer orderId;

    @Getter
    @Setter
    private Order selectedOrder;

    @Inject
    private PurchaseHistoryUtility purchaseHistoryUtility;

    @PostConstruct
    public void init() {
        selectedOrder = getOrderById(orderId);
    }

    public Order getOrderById(Integer oId){
        return purchaseHistoryUtility.getOrder(oId);
    }

}
