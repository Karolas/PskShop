package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class PaymentFront implements Serializable{
    @Getter
    @Setter
    private String cardHolder; //Name and surname

    @Getter
    @Setter
    private String cardNumber;

    @Getter
    @Setter
    private Integer expirationYear;

    @Getter
    @Setter
    private Integer expirationMonth;

    @Getter
    @Setter
    private String cvv;
}

