package psk.businessLogic.cartLogic;

import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.accountLogic.AccountAccessUtilityBase;
import psk.database.entities.CartProducts;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Decorator
public abstract class cartAccAccessUtilityDecorator implements AccountAccessUtility, Serializable {

    @Inject
    private CartUtility cartUtility;

    @Inject
    @Delegate
    @Any
    private AccountAccessUtilityBase accountAccessUtility;

    @Override
    public void loginAccount(String email, String password) {
        List<CartProducts> cartProducts = cartUtility.getCartProducts();
        accountAccessUtility.loginAccount(email, password);
        if(!accountAccessUtility.isBlocked())
            cartUtility.mergeCart(cartProducts);
    }
}
