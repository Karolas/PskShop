package psk.front;

import lombok.Getter;
import psk.Utilities.MessageHandler;
import psk.businessLogic.productLogic.ProductUtility;
import psk.businessLogic.productLogic.ProductUtilityBase;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class ProductDetailFront implements Serializable {
    @Inject
    private ProductUtility productUtility;

    @Inject
    MessageHandler messageHandler;

    @Getter
    private Product product;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        Integer productId = new Integer(params.get("productId"));
        product = productUtility.getProduct(productId);
    }
}
