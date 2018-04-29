package psk.front;

import psk.businessLogic.AccountAccessUtility;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class ProfileFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Inject
    @LoggedIn
    private Account account;

    public void updateProfile() {
        accountAccessUtility.updateAccount(account);
    }
}
