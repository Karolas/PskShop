package psk.businessLogic;

import lombok.Getter;
import lombok.Setter;
import org.apache.deltaspike.security.api.authorization.Secures;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import psk.businessLogic.authentication.AccountActive;
import psk.businessLogic.authentication.AccountAdmin;
import psk.businessLogic.authentication.AccountBlocked;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Named
@SessionScoped
public class AccountAccessUtility implements Serializable {
    @Inject
    private AccountDAO accountDAO;

    @Inject
    private Provider<AuthenticatedAccountHolder> authenticatedAccountHolderProvider;

    private String baseRequestUri;

    @Getter
    @Setter
    private int testCounter;

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
            authenticatedAccountHolderProvider.get().initUser(email);
        } catch (ServletException e) {
            Messages.addGlobalWarn("Wrong user name or password. Please try again.");
            return;
        }

//        try {
//            Faces.redirect(baseRequestUri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

    public boolean isAdmin() {
        return authenticatedAccountHolderProvider.get().isAdmin();
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
