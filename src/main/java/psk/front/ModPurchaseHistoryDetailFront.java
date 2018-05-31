package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.Param;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtilityBase;
import psk.database.entities.*;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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
