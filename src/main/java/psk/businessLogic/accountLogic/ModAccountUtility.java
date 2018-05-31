package psk.businessLogic.accountLogic;

import psk.database.entities.Account;

public interface ModAccountUtility {
    void BlockUser(Account account);
    void UnblockUser(Account account);
}
