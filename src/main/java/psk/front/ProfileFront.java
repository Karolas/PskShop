package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.Utilities.MessageHandler;
import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;

@ViewScoped
@Named
public class ProfileFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Inject
    MessageHandler messageHandler;

    @Inject
    @LoggedIn
    @Getter
    private Account account;

    @Getter
    private Account conflictingAccount;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String repeatPassword;

    @Transactional
    public void updateProfile(ActionEvent actionEvent) {
        conflictingAccount = null;
        try{
            account = accountAccessUtility.updateAccount(account);
            messageHandler.addMessage("Successful", "Profile has been updated!");
        } catch (OptimisticLockException oe) {
            messageHandler.addErrorMessage("Update was not successful!", "We got concurrent updates!");
            updateProfileOptLock();
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void updateProfileOptLock() {
        conflictingAccount = accountAccessUtility.getAccount(account);
        account.setOptLockVersion(conflictingAccount.getOptLockVersion());
    }

    @Transactional
    public void updatePassword(ActionEvent actionEvent) {
        try{
            accountAccessUtility.updatePassword(account, password);
            messageHandler.addMessage("Successful", "New password has been set!");
        } catch (OptimisticLockException e){
            messageHandler.addErrorMessage("Error", "We got concurrent updates!");
        }
        password = null;
        repeatPassword = null;
    }
}
