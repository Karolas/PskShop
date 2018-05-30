package psk.businessLogic.accountLogic;

import psk.InterceptorLog;
import psk.database.entities.Account;

public interface AccountAccessUtility {
    @InterceptorLog
    void loginAccount(String email, String password);
    boolean isLoggedIn();
    boolean isBlocked();
    boolean isUser();
    boolean isAdmin();
    @InterceptorLog
    void logoutAccount();
    @InterceptorLog
    void addAccount(Account account, String password);
    @InterceptorLog
    Account updateAccount(Account account);
    @InterceptorLog
    void updatePassword(Account account, String password);
    Account getAccount(Account account);
}
