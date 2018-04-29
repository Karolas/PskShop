package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.AccountAccessUtility;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class RegistrationFront implements Serializable {
    @Inject
    private AccountAccessUtility accountAccessUtility;

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

    public void register() {
        if(password.equals(repeatPassword)) {
            Account account = new Account();
            account.setEmail(email);
            account.setTelephoneNr(telephoneNr);
            account.setPostalNr(postalNr);
            account.setAddress(address);
            account.setLastName(lastName);
            account.setFirstName(firstName);
            account.setRole("User");

            accountAccessUtility.addAccount(account, password);
        }
    }
}