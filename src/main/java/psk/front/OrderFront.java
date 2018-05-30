package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.Utilities.MessageHandler;
import psk.businessLogic.authentication.LoggedIn;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
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
import java.util.Date;
import java.util.List;

@ViewScoped
@Named
public class OrderFront implements Serializable {
    @Inject
    PurchaseHistoryUtility purchaseHistoryUtility;

    @Inject
    @LoggedIn
    Account account;

    @Inject
    MessageHandler messageHandler;

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
