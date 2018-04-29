package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.businessLogic.ModUserUtility;
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
    ModUserUtility modUserUtility;

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
            @Override
            public Account getRowData(String rowKey) {
                int intRowKey = Integer.parseInt(rowKey);
                for(Account account : lazyModel) {
                    if(account.getId() == intRowKey) {
                        return account;
                    }
                }
                return null;
            }
        };
        lazyModel.setRowCount(accountDAO.count(new HashMap<String, Object> ()));
    }

    public void block(){
        modUserUtility.BlockUser(this.lazyModel.getRowData());
    }

    public void unblock(){
        modUserUtility.UnblockUser(this.lazyModel.getRowData());
    }

}
