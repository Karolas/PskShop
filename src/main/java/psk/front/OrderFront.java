package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.authentication.LoggedIn;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
import psk.database.entities.Account;
import psk.database.entities.Order;
import psk.database.entities.OrderProduct;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named
public class OrderFront {
    @Inject
    PurchaseHistoryUtility purchaseHistoryUtility;

    @Inject
    @LoggedIn
    Account account;

    @Getter
    @Setter
    private List<Order> orderList = new ArrayList<>();

    @Getter
    @Setter
    private List<OrderProduct> orderProductList = new ArrayList<>();

    @PostConstruct
    private void init(){
        orderList = purchaseHistoryUtility.getAllOrders(account);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setName("Preke");
        orderProduct.setAmount(20);
        orderProduct.setPrice(new BigDecimal(20));
        orderProductList.add(orderProduct);
        orderProductList.add(orderProduct);
    }
}
