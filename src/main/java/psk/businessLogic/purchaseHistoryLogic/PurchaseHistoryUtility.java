package psk.businessLogic.purchaseHistoryLogic;

import org.primefaces.model.SortOrder;
import psk.database.entities.Account;
import psk.database.entities.Order;

import java.util.List;
import java.util.Map;

public interface PurchaseHistoryUtility {
    Order getOrder(Integer orderId);
    int getProductsCount(String nameCriteria);
    List<Order> getAllOrders(Account account);
    int getProductsCountByFilters(Map<String, Object> filters);
    List<Order> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
    void updateOrder(Order order);
    void addOrder(String orderId, String timestamp);
}
