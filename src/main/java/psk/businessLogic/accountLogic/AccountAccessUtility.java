package psk.businessLogic.accountLogic;

import psk.database.entities.Account;

public interface AccountAccessUtility {
    void loginAccount(String email, String password);
    boolean isLoggedIn();
    boolean isBlocked();
    boolean isUser();
    boolean isAdmin();
    void logoutAccount();
    void addAccount(Account account, String password);
    Account updateAccount(Account account);
    void updatePassword(Account account, String password);
    Account getAccount(Account account);
}
