package psk.businessLogic.accountLogic;

import psk.InterceptorLog;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@InterceptorLog
public class ModAccountUtilityBase implements Serializable, ModAccountUtility{
    @Inject
    private AccountDAO accountDAO;

    public void BlockUser(Account account){
        account.setRole("Blocked");
        accountDAO.updateAccount(account);
    }
    public void UnblockUser(Account account){
        account.setRole("User");
        accountDAO.updateAccount(account);
    }
}
