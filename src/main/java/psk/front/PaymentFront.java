package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.json.JSONObject;
import psk.Utilities.MessageHandler;
import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.authentication.LoggedIn;
import psk.businessLogic.cartLogic.CartUtility;
import psk.businessLogic.purchaseHistoryLogic.PurchaseHistoryUtility;
import psk.database.entities.Account;
import psk.front.rest.PaymentRest;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.BigDecimalConverter;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

@ViewScoped
@Named
public class PaymentFront implements Serializable{
    @Inject
    private PaymentRest paymentService;

    @Inject
    private CartUtility cartUtility;

    @Inject
    MessageHandler messageHandler;

    @Inject
    private PurchaseHistoryUtility purchaseHistoryUtility;

    @Getter
    @Setter
    private String cardHolder; //Name and surname

    @Getter
    @Setter
    private String cardNumber;

    @Getter
    @Setter
    private int expirationYear;

    @Getter
    @Setter
    private int expirationMonth;

    @Getter
    @Setter
    private String cvv;

    public void pay() {
        JSONObject response = paymentService.postProcessPayment(
                convertToCents(cartUtility.getCartFullPrice()),
                cardNumber, cardHolder, expirationYear, expirationMonth, cvv);

        processResponse(response);
    }

    private int convertToCents(BigDecimal price) {
        BigDecimal centsBig = price.multiply(new BigDecimal(100));

        return centsBig.intValueExact();
    }

    private void processResponse(JSONObject response) {
        int status = response.getInt("status");

        if(status == 201) {
            purchaseHistoryUtility.addOrder(
                    response.getString("id"),
                    response.getString("created_at"));
        } else {
            throwErrorMessage(response.getString("message"));
        }
    }

    private void throwErrorMessage(String message) {
        messageHandler.addErrorMessage("Error", message);
    }
}

