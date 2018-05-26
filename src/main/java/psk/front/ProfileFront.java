package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class ProfileFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Inject
    @LoggedIn
    @Getter
    private Account account;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String repeatPassword;

    public void updateProfile(ActionEvent actionEvent) {
        accountAccessUtility.updateAccount(account);
    }

    public void updatePassword(ActionEvent actionEvent) {
        accountAccessUtility.updatePassword(account, password);

        password = null;
        repeatPassword = null;
    }
}
