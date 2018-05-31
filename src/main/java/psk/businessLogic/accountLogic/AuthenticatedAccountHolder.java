package psk.businessLogic.accountLogic;

import psk.database.entities.Account;

public interface AuthenticatedAccountHolder {
    void initUser(String email);
    void updatePassword(Account account, String password);
    String hashPassword(String password);
    boolean isLoggedIn();
    boolean isBlocked();
    boolean isUser();
    boolean isAdmin();
}
