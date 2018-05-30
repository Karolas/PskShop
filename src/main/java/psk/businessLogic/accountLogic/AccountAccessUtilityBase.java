package psk.businessLogic.accountLogic;

import org.omnifaces.util.Faces;
import psk.InterceptorLog;
import psk.Utilities.MessageHandler;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.OptimisticLockException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class AccountAccessUtilityBase implements Serializable, AccountAccessUtility {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private MessageHandler messageHandler;

    @Inject
    private Provider<AuthenticatedAccountHolder> authenticatedAccountHolderProvider;

    @InterceptorLog
    public void loginAccount(String email, String password) {
        Faces.invalidateSession();
        Faces.getSession(true);

        String hashedPassword = authenticatedAccountHolderProvider.get().hashPassword(password);

        try {
            Faces.login(email, hashedPassword);
            authenticatedAccountHolderProvider.get().initUser(email);
        } catch (ServletException e) {
            messageHandler.addErrorMessage("Login Error: Wrong user name or password. Please try again.", "Wrong user name or password.");
        }
    }

    public void logoutAccount() {
        Faces.invalidateSession();
        try {
            if(!isBlocked()) {
                Faces.redirect(Faces.getRequestBaseURL());
            } else {
                Faces.redirect("login.xhtml?blocked=true");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account, String password) {
        account.setHashedPassword(authenticatedAccountHolderProvider.get().hashPassword(password));

        accountDAO.insertAccount(account);
    }

    public Account updateAccount(Account account){
        Account account1;
        try{
            account1 = accountDAO.updateAccount(account);
            authenticatedAccountHolderProvider.get().initUser(account.getEmail());
        } catch (Exception e){
            throw new OptimisticLockException(e.getMessage());
        }

        return account1;
    }

    public void updatePassword(Account account, String password) {
        authenticatedAccountHolderProvider.get().updatePassword(account, password);
    }

    public boolean isLoggedIn() {
        return authenticatedAccountHolderProvider.get().isLoggedIn();
    }

    public boolean isBlocked() {
        return authenticatedAccountHolderProvider.get().isBlocked();
    }

    public boolean isUser() {
        return authenticatedAccountHolderProvider.get().isUser();
    }

    public Account getAccount(Account account) {
        return accountDAO.selectAccountByEmail(account.getEmail());
    }
    public boolean isAdmin() {
        return authenticatedAccountHolderProvider.get().isAdmin();
    }

}
