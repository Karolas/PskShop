package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.Utilities.MessageHandler;
import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.cartLogic.CartUtility;
import psk.businessLogic.cartLogic.CartUtilityBase;
import psk.database.entities.Account;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;

@ViewScoped
@Named
public class RegistrationFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Inject
    MessageHandler messageHandler;

    @Inject
    private CartUtility cartUtility;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String repeatPassword;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String postalNr;

    @Getter
    @Setter
    private String telephoneNr;

    @Transactional
    public void register(ActionEvent actionEvent) {
        Account account = new Account();
        account.setEmail(email);
        account.setTelephoneNr(telephoneNr);
        account.setPostalNr(postalNr);
        account.setAddress(address);
        account.setLastName(lastName);
        account.setFirstName(firstName);
        account.setRole("User");

        accountAccessUtility.addAccount(account, password);

        cartUtility.createCart(account.getId());

        registerRedirect();
    }

    private void registerRedirect() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
