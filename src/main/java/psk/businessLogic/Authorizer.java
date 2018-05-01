package psk.businessLogic;

import org.apache.deltaspike.security.api.authorization.Secures;
import psk.businessLogic.authentication.AccountActive;
import psk.businessLogic.authentication.AccountAdmin;
import psk.businessLogic.authentication.AccountBlocked;
import psk.businessLogic.authentication.LoggedIn;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Authorizer {

    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Secures
    @LoggedIn
    public boolean isLoggedIn() {
        return accountAccessUtility.isLoggedIn();
    }

    @Secures
    @AccountBlocked
    public boolean isBlocked() {
        return accountAccessUtility.isBlocked();
    }

    @Secures
    @AccountActive
    public boolean isUser() {
        return accountAccessUtility.isUser();
    }

    @Secures
    @AccountAdmin
    public boolean isAdmin() {
        return accountAccessUtility.isAdmin();
    }
}
