package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.apache.deltaspike.proxy.api.EnableInterceptors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;
import psk.database.entities.AccountLazyModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@SessionScoped
@Named
public class ModUserFront implements Serializable{
    @Getter
    @Setter
    List<Account> accounts;
    LazyDataModel<Account> lazyModel;
//    AccountLazyModel dataModel = new AccountLazyModel();

//    public LazyDataModel<Account> getModel() {
//        return dataModel;
//    }
    public LazyDataModel<Account> getLazyModel() {
        return lazyModel;
    }
    private LazyDataModel<Account> model;
    @Inject
    AccountDAO accountDAO;
    @PostConstruct
    public void init() {
        this.accounts = accountDAO.getAll();
        lazyModel = new AccountLazyModel(this.accounts);
//        this.model = new LazyDataModel<Account>() {
//            private static final long serialVersionUID = 1L;
//            @Override
//            public List<Account> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//                List<Account> result = accountDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
//                model.setRowCount(accountDAO.count(sortField, sortOrder, filters));
//                return result;
//            }
//        };
    }

//    @PostConstruct
//    public void init() {
//        accounts = new ArrayList<>();
//        for(Integer i=0; i<100; i++){
//            Account a = new Account();
//            a.setEmail(i.toString());
//            System.out.println(a);
//            accounts.add(a);
//        }
//    }
}
