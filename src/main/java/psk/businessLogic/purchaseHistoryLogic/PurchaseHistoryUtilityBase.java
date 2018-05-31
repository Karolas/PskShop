package psk.businessLogic.purchaseHistoryLogic;

import org.primefaces.model.SortOrder;
import psk.InterceptorLog;
import psk.businessLogic.authentication.LoggedIn;
import psk.businessLogic.cartLogic.CartUtility;
import psk.businessLogic.cartLogic.CartUtilityBase;
import psk.database.dao.AccountDAO;
import psk.database.dao.OrderDAO;
import psk.database.dao.OrderProductDAO;
import psk.database.entities.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
//@InterceptorLog
public class PurchaseHistoryUtilityBase implements Serializable, PurchaseHistoryUtility {

    @Inject
    @LoggedIn
    private Account account;

    @Inject
    private CartUtility cartUtility;

    @Inject
    private OrderDAO orderDAO;

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private OrderProductDAO orderProductDAO;
    
    public Order getOrder(Integer orderId) {
        return orderDAO.getOrderById(orderId);
    }

    public int getProductsCount(String nameCriteria) {
        return orderDAO.count(nameCriteria);
    }

    public List<Order> getAllOrders(Account account){
        return orderDAO.getAllOrders(account);
    }

    public int getProductsCountByFilters(Map<String, Object> filters){
        return orderDAO.countWithilters(filters);
    }
    public List<Order> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return orderDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
    }
    public void updateOrder(Order order){
        orderDAO.updateOrder(order);
    }

    @InterceptorLog
    @Transactional
    public void addOrder(String orderId, String timestamp) {
        Account accountDb = accountDAO.selectAccountById(account.getId());

        Order order = new Order();
        order.setStatus("Pending");
        order.setAccount(accountDb);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSS'Z'");
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp orderTimestamp = new java.sql.Timestamp(parsedDate.getTime());

            order.setOrderCreated(orderTimestamp);
        } catch (ParseException e) {
            order.setOrderCreated(null);
        }

        orderDAO.createOrder(order);

        for (CartProducts cartProduct: cartUtility.getCartProducts()) {
            OrderProduct orderProduct = new OrderProduct();

            orderProduct.setName(cartProduct.getProduct().getName());
            orderProduct.setPrice(cartProduct.getProduct().getPrice());
            orderProduct.setAmount(cartProduct.getAmount());
            orderProduct.setOrder(order);

            orderProductDAO.insertOrderProduct(orderProduct);
        }
    }

//    public void createProduct(Order order) {
//        orderDAO.insertProduct(order);
//    }
//    public void deleteProduct(Order order) {
//        orderDAO.deleteProduct(order.getId());
//    }
}
