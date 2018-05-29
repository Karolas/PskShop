package psk.businessLogic.cartLogic;

import psk.businessLogic.accountLogic.AccountAccessUtility;
import psk.businessLogic.authentication.LoggedIn;
import psk.database.dao.CartDAO;
import psk.database.dao.CartProductDAO;
import psk.database.entities.Account;
import psk.database.entities.Cart;
import psk.database.entities.CartProducts;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class CartUtility implements Serializable {

    @Inject
    private AccountAccessUtility accountAccessUtility;

    @Inject
    @LoggedIn
    private Account account;

    private Integer cartId;

    @Inject
    private CartDAO cartDAO;

    @Inject
    private CartProductDAO cartProductDAO;

    private Set<CartProducts> offlineCartProducts = new HashSet<>();

    @PostConstruct
    public void init() {
        if (accountAccessUtility.isLoggedIn()) {
            cartId = cartDAO.getCartByAccId(account.getId()).getId();
        }
    }

    public BigDecimal getCartFullPrice() {
        BigDecimal price = new BigDecimal(0);

        for (CartProducts cartProduct: getCartProducts()) {
            price = price.add(cartProduct.getProduct().getPrice().multiply(new BigDecimal(cartProduct.getAmount())));
        }

        return price;
    }

    public Set<CartProducts> getCartProducts() {
        if(accountAccessUtility.isLoggedIn())
        {
            Cart cart = cartDAO.getCartById(cartId);

            Set<CartProducts> cartProducts = cart.getProducts();

            return cartProducts;
        }
        else
            return offlineCartProducts;
    }

    @Transactional
    public void addProductToCart(Product product, Integer amount) {
        if(accountAccessUtility.isLoggedIn()) {
            Cart cart = cartDAO.getCartById(cartId);

            if(cartProductDAO.isProductInCart(cart, product)) {
                CartProducts cartProduct = cartProductDAO.selectCartProduct(cart, product);

                cartProductDAO.updateAmount(cartProduct, cartProduct.getAmount() + amount);
            } else {
                cartProductDAO.insertCartProduct(cart, product, amount);
            }

        } else {
            boolean isProductInCart = false;

            for(CartProducts cartProduct: offlineCartProducts) {
                if(cartProduct.getProduct().getId().equals(product.getId())) {
                    cartProduct.setAmount(cartProduct.getAmount() + amount);
                    isProductInCart = true;
                    break;
                }
            }

            if(!isProductInCart) {
                CartProducts cartProduct = new CartProducts();
                cartProduct.setProduct(product);
                cartProduct.setAmount(amount);
                offlineCartProducts.add(cartProduct);
            }
        }

    }

    @Transactional
    public void removeFromCart(CartProducts cartProduct) {
        if(accountAccessUtility.isLoggedIn()) {
            cartProductDAO.deleteCartProduct(cartProduct);
        } else {
            offlineCartProducts.remove(cartProduct);
        }
    }

    @Transactional
    public void createCart(Integer accountId) {
        Cart cart = new Cart();
        cart.setAccountId(accountId);

        cartDAO.createCart(cart);
    }

    public void mergeCart(Set<CartProducts> cartProducts) {
        for (CartProducts cartProduct: cartProducts) {
            addProductToCart(cartProduct.getProduct(), cartProduct.getAmount());
        }
    }
}
