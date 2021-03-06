package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import psk.Utilities.MessageHandler;
import psk.businessLogic.accountLogic.AccountAccessUtility;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
public class LoginFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Inject
    MessageHandler messageHandler;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    public void login(ActionEvent actionEvent) {
        accountAccessUtility.loginAccount(email, password);
    }

    public void redirectLogin() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(accountAccessUtility.isLoggedIn()) {
                if(accountAccessUtility.isAdmin()) {
                    ec.redirect("index.xhtml");
                } else if(accountAccessUtility.isUser()) {
                    ec.redirect("index.xhtml");
                } else if(accountAccessUtility.isBlocked()) {
                    accountAccessUtility.logoutAccount();
                } else {
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
