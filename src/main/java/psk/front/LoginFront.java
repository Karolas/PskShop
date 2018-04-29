package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.AccountAccessUtility;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class LoginFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    public void login(ActionEvent actionEvent) {
        accountAccessUtility.loginAccount(email, password);

        password = null;
    }

    public void logout() {
        accountAccessUtility.logoutAccount();
    }
}
