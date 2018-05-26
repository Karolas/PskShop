package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.accountLogic.AccountAccessUtility;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
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

        if(accountAccessUtility.isLoggedIn()) {
            password = null;
        }
    }

    public void redirectLogin() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(accountAccessUtility.isLoggedIn()) {
                if(accountAccessUtility.isAdmin()) {
                    ec.redirect("index.xhtml");
                } else if(accountAccessUtility.isUser()) {
                    ec.redirect("index.xhtml");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        accountAccessUtility.logoutAccount();
    }
}
