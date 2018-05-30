package psk.businessLogic.accountLogic;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import psk.Utilities.MessageHandler;
import psk.businessLogic.cartLogic.CartUtility;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;
import psk.database.entities.CartProducts;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import java.util.List;

@Named
@SessionScoped
public class AccountAccessUtilityBase implements Serializable, AccountAccessUtility {
    @Inject
    private AccountDAO accountDAO;

    @Inject
    private MessageHandler messageHandler;

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
        //List<CartProducts> cartProducts = cartUtility.getCartProducts();

        Faces.invalidateSession();
        Faces.getSession(true);

        String hashedPassword = hashPassword(password);

        try {
            Faces.login(email, hashedPassword);
            authenticatedAccountHolderProvider.get().initUser(email);

            //cartUtility.mergeCart(cartProducts);
        } catch (ServletException e) {

            messageHandler.addErrorMessage("Login Error: Wrong user name or password. Please try again.", "Wrong user name or password.");
//            Messages.addGlobalWarn("Wrong user name or password. Please try again.");
        }

        if(isBlocked()) {
            logoutAccount();
        }
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

        authenticatedAccountHolderProvider.get().initUser(account.getEmail());
    }

    public void updatePassword(Account account, String password) {
        String hashedPassword = hashPassword(password);

        accountDAO.updatePassword(account, hashedPassword);
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
