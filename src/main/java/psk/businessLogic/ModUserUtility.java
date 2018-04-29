package psk.businessLogic;

import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ModUserUtility implements Serializable{
    @Inject
    AccountDAO accountDAO;

    public void BlockUser(Account account){
        account.setRole("Blocked");
        accountDAO.updateAccount(account);
    }
    public void UnblockUser(Account account){
        account.setRole("User");
        accountDAO.updateAccount(account);
    }
}
