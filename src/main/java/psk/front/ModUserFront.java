package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

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
    LazyDataModel<Account> lazyModel;

    @Inject
    AccountDAO accountDAO;
    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<Account> () {

            @Override
            public List<Account> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                lazyModel.setRowCount(accountDAO.count(filters));
                return accountDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
        };
        lazyModel.setRowCount(accountDAO.count(new HashMap<String, Object> ()));
    }
}
