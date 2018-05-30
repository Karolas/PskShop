package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.businessLogic.ModAcountUtility;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@ViewScoped
@Named
public class ModAccountFront implements Serializable {
    @Getter
    @Setter
    private LazyDataModel<Account> lazyModel;

    @Inject
    private ModAcountUtility modUserUtility;

    @Inject
    private AccountDAO accountDAO;

    @PostConstruct
    public void init() {
        lazyModel = new LazyDataModel<Account>() {

            @Override
            public List<Account> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                lazyModel.setRowCount(accountDAO.count(filters));
                return accountDAO.getResultList(first, pageSize, sortField, sortOrder, filters);
            }
//            @Override
//            public Account getRowData(String rowKey) {
//                int intRowKey = Integer.parseInt(rowKey);
//                for(Account account : lazyModel) {
//                    if(account.getId() == intRowKey) {
//                        return account;
//                    }
//                }
//                return null;
//            }
        };
        lazyModel.setRowCount(accountDAO.count(new HashMap<String, Object>()));
    }

    public void block() {
        modUserUtility.BlockUser(this.lazyModel.getRowData());
        this.lazyModel.getRowData().setRole("Blocked");
        this.updateTable();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Success") );
    }

    public void unblock() {
        modUserUtility.UnblockUser(this.lazyModel.getRowData());
        this.lazyModel.getRowData().setRole("User");
        this.updateTable();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Success") );
    }

    public void updateTable() {
        RequestContext.getCurrentInstance().update("usersForm:lazyUsers");
    }
}
