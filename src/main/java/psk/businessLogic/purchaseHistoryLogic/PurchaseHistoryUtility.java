package psk.businessLogic.purchaseHistoryLogic;

import org.primefaces.model.SortOrder;
import psk.database.dao.OrderDAO;
import psk.database.entities.Account;
import psk.database.entities.Order;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class PurchaseHistoryUtility implements Serializable {

    @Inject
    private OrderDAO orderDAO;

    public int getProductsCount(String nameCriteria) {
        return orderDAO.count(nameCriteria);
    }

    public List<Order> getAllOrders(Account account){
        return orderDAO.getAllOrders(account);
    }

//    public List<Order> getProductsPage(int first, int pageSize, String nameCriteria) {
//        return orderDAO.getResultList(first, pageSize, nameCriteria);
//    }

    public int getProductsCountByFilters(Map<String, Object> filters){
        return orderDAO.countWithilters(filters);
    }
    public List<Order> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return orderDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
    }
    public void updateOrder(Order order){
        orderDAO.updateOrder(order);
    }
//    public void createProduct(Order order) {
//        orderDAO.insertProduct(order);
//    }
//    public void deleteProduct(Order order) {
//        orderDAO.deleteProduct(order.getId());
//    }
}
