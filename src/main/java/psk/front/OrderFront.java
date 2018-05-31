package psk.front;

import lombok.Getter;
import psk.Utilities.MessageHandler;
import psk.businessLogic.authentication.LoggedIn;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtilityBase;
import psk.database.entities.Account;
import psk.database.entities.Order;
import psk.database.entities.OrderProduct;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class OrderFront implements Serializable {
    @Inject
    private PurchaseHistoryUtility purchaseHistoryUtility;

    @Inject
    @LoggedIn
    private Account account;

    @Inject
    private MessageHandler messageHandler;

    @Getter
    private List<Order> orderList = new ArrayList<>();

    @PostConstruct
    private void init(){
        orderList = purchaseHistoryUtility.getAllOrders(account);
    }

    public BigDecimal getTotalPrice(Integer amount, BigDecimal price){
        return price.multiply(new BigDecimal(amount));
    }

    public BigDecimal getTotalPriceOfOrder(Order order) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderProduct orderProduct: order.getProducts()) {
            BigDecimal productPrice = orderProduct.getPrice()
                    .multiply(new BigDecimal(orderProduct.getAmount()));
            totalPrice = totalPrice.add(productPrice);
        }
        return totalPrice;
    }
}
