package psk.businessLogic.accountLogic;

import lombok.Getter;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("accountHolder")
@SessionScoped
public class AuthenticatedAccountHolder implements Serializable {
    @Inject
    private AccountDAO accountDAO;

    @Getter
    private Account authenticatedAccount;

    void initUser(String email) {
        authenticatedAccount = accountDAO.selectAccountByEmail(email);
    }

    @Produces
    @Named("authenticatedAccount")
    @LoggedIn
    private Account produceAuthenticatedUser() {
        return authenticatedAccount;
    }

    public boolean isLoggedIn() {
        return authenticatedAccount != null;
    }

    public boolean isBlocked() {
        return authenticatedAccount != null
                && authenticatedAccount.getRole().equals("Blocked");
    }

    public boolean isUser() {
        return authenticatedAccount != null
                && authenticatedAccount.getRole().equals("User");
    }

    public boolean isAdmin() {
        return authenticatedAccount != null
                && authenticatedAccount.getRole().equals("Admin");
    }
}
