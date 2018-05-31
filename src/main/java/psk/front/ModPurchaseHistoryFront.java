package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.Utilities.MessageHandler;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtilityBase;
import psk.database.entities.Order;

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
public class ModPurchaseHistoryFront implements Serializable {

    @Getter
    @Setter
    private LazyDataModel<Order> lazyModel;

    @Inject
    private PurchaseHistoryUtility purchaseHistoryUtility;

    @Inject
    private MessageHandler messageHandler;

    @Getter
    @Setter
    private List<String> orderStatuses = Arrays.asList("Pending", "Accepted", "In progress", "Sent", "Delivered");

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<Order>() {

            @Override
            public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                lazyModel.setRowCount(purchaseHistoryUtility.getProductsCountByFilters(filters));
                return purchaseHistoryUtility.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
        };
    }

    public void redirectToOrderView(Order order) {
        System.out.println(order.getId());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect("purchaseHistoryDetail.xhtml?orderId=" + order.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStatusChange(Order order){
        purchaseHistoryUtility.updateOrder(order);
        messageHandler.addMessage("Success", "Status has been changed to " + order.getStatus());
    }
}
