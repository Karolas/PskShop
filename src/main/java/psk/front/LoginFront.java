package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.UserAccessUtility;
import psk.database.dao.AccountDAO;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;

@SessionScoped
@Named
public class LoginFront implements Serializable {
    @Inject
    private UserAccessUtility userAccessUtility;

    @Inject
    private AccountDAO accountDAO;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    public void login(ActionEvent actionEvent) {
        Account account = accountDAO.selectAccountByEmail("test");

        userAccessUtility.loginAccount(email, password);

        password = null;
    }

    public void logout() {
        userAccessUtility.logoutAccount();
    }
}
