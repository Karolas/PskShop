package psk.businessLogic.accountLogic;

import lombok.Getter;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

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


    void updatePassword(Account account, String password) {
        String hashedPassword = hashPassword(password);

        accountDAO.updatePassword(account, hashedPassword);
    }

    String hashPassword(String password) {
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
