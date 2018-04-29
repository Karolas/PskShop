package psk.businessLogic;

import org.apache.deltaspike.security.api.authorization.Secures;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import psk.businessLogic.authentication.AccountActive;
import psk.businessLogic.authentication.AccountAdmin;
import psk.businessLogic.authentication.AccountBlocked;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.dao.AccountDAO;
import psk.database.dao.AccountGroupsDAO;
import psk.database.entities.Account;
import psk.database.entities.accountGroup.AccountGroups;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Named
@SessionScoped
public class AccountAccessUtility implements Serializable {
    @Inject
    private AccountDAO accountDAO;

    @Inject
    private AccountGroupsDAO accountGroupsDAO;

    private Account authenticatedAccount;

    private String baseRequestUri;

    @PostConstruct
    public void postConstruct() {
        baseRequestUri = Faces.getRequestURI();
    }

    public void loginAccount(String email, String password) {
        Faces.invalidateSession();
        Faces.getSession(true);

        String hashedPassword = hashPassword(password);

        try {
            Faces.login(email, hashedPassword);
            setAuthenticatedAccount(email);
        } catch (ServletException e) {
            Messages.addGlobalWarn("Wrong user name or password. Please try again.");
            return;
        }

        try {
            Faces.redirect(baseRequestUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutAccount() {
        Faces.invalidateSession();

        try {
            Faces.redirect(Faces.getRequestBaseURL()); // arba kitur, kur norime patekti po logout.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account, String password) {
        account.setHashedPassword(hashPassword(password));

        accountDAO.insertAccount(account);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    @Secures
    @LoggedIn
    public boolean isLoggedIn() {
        return authenticatedAccount != null;
    }

    @Secures
    @AccountBlocked
    public boolean isBlocked() {
        return authenticatedAccount != null
                && authenticatedAccount.getRole().equals("Blocked");
    }

    @Secures
    @AccountActive
    public boolean isUser() {
        return authenticatedAccount != null
                && authenticatedAccount.getRole().equals("User");
    }

    @Secures
    @AccountAdmin
    public boolean isAdmin() {
        return authenticatedAccount != null
                && authenticatedAccount.getRole().equals("Admin");
    }

    private void setAuthenticatedAccount(String email) {
        authenticatedAccount = accountDAO.selectAccountByEmail(email);
    }

    @Produces
    @Named("authenticatedAccount")
    @LoggedIn
    @SessionScoped
    private Account produceAuthenticatedAccount() {
        return authenticatedAccount;
    }

    private String hashPassword(String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), new byte[16], 10000, 128);

        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            return enc.encodeToString(hash);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }


}
